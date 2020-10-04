package com.arturoeanton.openscimv2.components;

import com.arturoeanton.openscimv2.commons.Config;
import com.arturoeanton.openscimv2.commons.Transformer;
import com.arturoeanton.openscimv2.filter.ScimFilterBaseVisitor;
import com.arturoeanton.openscimv2.filter.ScimFilterParser;
import com.arturoeanton.openscimv2.model.Schema;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class Filter extends ScimFilterBaseVisitor<BasicDBObject> {

    private  String prefix;
    public BasicDBObject query = new BasicDBObject();
    public boolean flagNot;
    public Schema schema;
    Schemas schemas;


    public Filter(Schemas schemas, Schema schema, String prefix, boolean flagNot) {
        this.schemas = schemas;
        this.flagNot = flagNot;
        this.schema = schema;
        this.prefix = prefix;
    }

    public Filter(Schemas schemas, Schema schema) {
        this.schemas = schemas;
        this.flagNot = false;
        this.schema = schema;
        this.prefix = "";
    }


    public BasicDBObject visitScimFilter(ScimFilterParser.ScimFilterContext ctx) {
        super.visitChildren(ctx);
        return query;
    }

    public BasicDBObject visitLPAREN_EXPR_RPAREN(ScimFilterParser.LPAREN_EXPR_RPARENContext ctx) {
        super.visitChildren(ctx);
        return query;
    }


    @Override
    public BasicDBObject visitNOT_EXPR(ScimFilterParser.NOT_EXPRContext ctx) {
        return (new Filter(schemas, schema, prefix, true)).visit(ctx.expression());
    }

    @Override
    public BasicDBObject visitEXPR_OR_EXPR(ScimFilterParser.EXPR_OR_EXPRContext ctx) {
        return makeListExpAndOr("$or", ctx.expression());
    }

    private BasicDBObject makeListExpAndOr(String operator, List<ScimFilterParser.ExpressionContext> expression) {
        BasicDBList list = new BasicDBList();
        for (var exp : expression) {
            var queryExp = (new Filter(schemas, schema,prefix, flagNot)).visit(exp);
            list.add(queryExp);
        }
        query.put(operator, list);
        return query;
    }

    @Override
    public BasicDBObject visitEXPR_AND_EXPR(ScimFilterParser.EXPR_AND_EXPRContext ctx) {
        return makeListExpAndOr("$and", ctx.expression());
    }

    public BasicDBObject visitATTR_PR(ScimFilterParser.ATTR_PRContext ctx) {
        var o = new BasicDBObject();
        o.put("$exists", !flagNot);
        var attrnamepath = Transformer.internal(ctx.ATTRNAME().getText());
        query.put(attrnamepath, o);
        return query;
    }

    public BasicDBObject visitLBRAC_EXPR_RBRAC(ScimFilterParser.LBRAC_EXPR_RBRACContext ctx) {
        var prefix = Transformer.internal(ctx.ATTRNAME().getText())+".";
        var temp = (new Filter( schemas, schema,prefix, flagNot)).visit(ctx.expression());
        for (var k : temp.keySet()) {
            query.put(k, temp.get(k));
        }
        return query;
    }

    @Override
    public BasicDBObject visitATTR_OPER_VALUE(ScimFilterParser.ATTR_OPER_VALUEContext ctx) {
        var internalField = prefix+ Transformer.internal(ctx.ATTRNAME().getText());
        var operator = ctx.operator();
        var value = ctx.value().getText();
        var attribute = schemas.pathFieldToAttribute.get(Transformer.external(internalField));
        if (attribute == null) {
            attribute = schemas.pathFieldToAttribute.get(schema.getId() + ":" + Transformer.external(internalField));
        }
        return makeQueryATTR_OPER(internalField, operator, value, attribute);
    }

    private BasicDBObject makeQueryATTR_OPER(String internalField, ScimFilterParser.OperatorContext operator, String criteria, com.arturoeanton.openscimv2.model.Attribute attribute) {
        Object value = criteria;
        if ("boolean".equalsIgnoreCase(attribute.getType())) {
            value = Boolean.parseBoolean(value.toString());
        }
        if ("integer".equalsIgnoreCase(attribute.getType())) {
            value = Integer.parseInt(value.toString());
        }
        if ("decimal".equalsIgnoreCase(attribute.getType())) {
            value = Float.parseFloat(value.toString());
        }
        if ("datetime".equalsIgnoreCase(attribute.getType())) {
            var format = Config.FORMAT_DATE;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
            var date = LocalDateTime.parse(value.toString(), dateFormatter);
            value = Timestamp.valueOf(date).getTime();
        }
        BasicDBObject term = new BasicDBObject();
        BasicDBObject not = new BasicDBObject();

        if (operator.EQ() != null) {
            term.put("$eq", value);
        } else if (operator.NE() != null) {
            term.put("$ne", value);
        } else if (operator.SW() != null) {
            term.put("$regex", Pattern.compile("^" + value + ".*$", Pattern.CASE_INSENSITIVE));
        } else if (operator.EW() != null) {
            term.put("$regex", Pattern.compile("^.*" + value + "$", Pattern.CASE_INSENSITIVE));
        } else if (operator.CO() != null) {
            term.put("$regex", Pattern.compile("^.*" + value + ".*$", Pattern.CASE_INSENSITIVE));

        } else if (operator.GE() != null) {
            term.put("$gte", value);
        } else if (operator.GT() != null) {
            term.put("$gt", value);
        } else if (operator.LE() != null) {
            term.put("$lte", value);
        } else if (operator.LT() != null) {
            term.put("$lt", value);
        }

        not.put("$not", term);

        query.put(internalField, flagNot ? not : term);
        return query;
    }

    @Override
    public BasicDBObject visitATTR_OPER_CRITERIA(ScimFilterParser.ATTR_OPER_CRITERIAContext ctx) {
        var internalField = prefix+ Transformer.internal(ctx.ATTRNAME().getText());
        var operator = ctx.operator();
        var criteria = ctx.criteria().getText().substring(1, ctx.criteria().getText().length() - 1);
        var attribute = schemas.pathFieldToAttribute.get(Transformer.external(internalField));
        if (attribute == null) {
            attribute = schemas.pathFieldToAttribute.get(schema.getId() + ":" + Transformer.external(internalField));
        }
        return makeQueryATTR_OPER(internalField, operator, criteria, attribute);
    }


}
