// Generated from /Users/arturoeliasanton/github/arturoeanton/openscimv2/src/main/resources/ScimFilter.g4 by ANTLR 4.8
package com.arturoeanton.openscimv2.filter;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ScimFilterParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ScimFilterVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ScimFilterParser#scimFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScimFilter(ScimFilterParser.ScimFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ATTR_PR}
	 * labeled alternative in {@link ScimFilterParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitATTR_PR(ScimFilterParser.ATTR_PRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LBRAC_EXPR_RBRAC}
	 * labeled alternative in {@link ScimFilterParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLBRAC_EXPR_RBRAC(ScimFilterParser.LBRAC_EXPR_RBRACContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EXPR_OR_EXPR}
	 * labeled alternative in {@link ScimFilterParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEXPR_OR_EXPR(ScimFilterParser.EXPR_OR_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NOT_EXPR}
	 * labeled alternative in {@link ScimFilterParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNOT_EXPR(ScimFilterParser.NOT_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EXPR_AND_EXPR}
	 * labeled alternative in {@link ScimFilterParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEXPR_AND_EXPR(ScimFilterParser.EXPR_AND_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ATTR_OPER_VALUE}
	 * labeled alternative in {@link ScimFilterParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitATTR_OPER_VALUE(ScimFilterParser.ATTR_OPER_VALUEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ATTR_OPER_CRITERIA}
	 * labeled alternative in {@link ScimFilterParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitATTR_OPER_CRITERIA(ScimFilterParser.ATTR_OPER_CRITERIAContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LPAREN_EXPR_RPAREN}
	 * labeled alternative in {@link ScimFilterParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLPAREN_EXPR_RPAREN(ScimFilterParser.LPAREN_EXPR_RPARENContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScimFilterParser#criteria}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCriteria(ScimFilterParser.CriteriaContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScimFilterParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(ScimFilterParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScimFilterParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(ScimFilterParser.OperatorContext ctx);
}