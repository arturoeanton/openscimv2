// Generated from /Users/arturoeliasanton/github/arturoeanton/openscimv2/src/main/resources/ScimFilter.g4 by ANTLR 4.8
package com.arturoeanton.openscimv2.filter;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ScimFilterParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, EQ=4, NE=5, CO=6, SW=7, EW=8, GT=9, LT=10, GE=11, 
		LE=12, NOT=13, AND=14, OR=15, PR=16, LPAREN=17, RPAREN=18, LBRAC=19, RBRAC=20, 
		WS=21, ATTRNAME=22, ANY=23, EOL=24;
	public static final int
		RULE_scimFilter = 0, RULE_expression = 1, RULE_criteria = 2, RULE_value = 3, 
		RULE_operator = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"scimFilter", "expression", "criteria", "value", "operator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\"'", "'true'", "'false'", null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "'('", "')'", "'['", "']'", 
			"' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "EQ", "NE", "CO", "SW", "EW", "GT", "LT", "GE", 
			"LE", "NOT", "AND", "OR", "PR", "LPAREN", "RPAREN", "LBRAC", "RBRAC", 
			"WS", "ATTRNAME", "ANY", "EOL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ScimFilter.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ScimFilterParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ScimFilterContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ScimFilterParser.EOF, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ScimFilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scimFilter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitScimFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScimFilterContext scimFilter() throws RecognitionException {
		ScimFilterContext _localctx = new ScimFilterContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_scimFilter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << LPAREN) | (1L << ATTRNAME))) != 0)) {
				{
				{
				setState(10);
				expression(0);
				}
				}
				setState(15);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(16);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ATTR_PRContext extends ExpressionContext {
		public TerminalNode ATTRNAME() { return getToken(ScimFilterParser.ATTRNAME, 0); }
		public TerminalNode PR() { return getToken(ScimFilterParser.PR, 0); }
		public List<TerminalNode> WS() { return getTokens(ScimFilterParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ScimFilterParser.WS, i);
		}
		public ATTR_PRContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitATTR_PR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LBRAC_EXPR_RBRACContext extends ExpressionContext {
		public TerminalNode ATTRNAME() { return getToken(ScimFilterParser.ATTRNAME, 0); }
		public TerminalNode LBRAC() { return getToken(ScimFilterParser.LBRAC, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRAC() { return getToken(ScimFilterParser.RBRAC, 0); }
		public List<TerminalNode> WS() { return getTokens(ScimFilterParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ScimFilterParser.WS, i);
		}
		public LBRAC_EXPR_RBRACContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitLBRAC_EXPR_RBRAC(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EXPR_OR_EXPRContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(ScimFilterParser.OR, 0); }
		public List<TerminalNode> WS() { return getTokens(ScimFilterParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ScimFilterParser.WS, i);
		}
		public EXPR_OR_EXPRContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitEXPR_OR_EXPR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NOT_EXPRContext extends ExpressionContext {
		public TerminalNode NOT() { return getToken(ScimFilterParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(ScimFilterParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ScimFilterParser.WS, i);
		}
		public NOT_EXPRContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitNOT_EXPR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EXPR_AND_EXPRContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(ScimFilterParser.AND, 0); }
		public List<TerminalNode> WS() { return getTokens(ScimFilterParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ScimFilterParser.WS, i);
		}
		public EXPR_AND_EXPRContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitEXPR_AND_EXPR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ATTR_OPER_VALUEContext extends ExpressionContext {
		public TerminalNode ATTRNAME() { return getToken(ScimFilterParser.ATTRNAME, 0); }
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(ScimFilterParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ScimFilterParser.WS, i);
		}
		public ATTR_OPER_VALUEContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitATTR_OPER_VALUE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ATTR_OPER_CRITERIAContext extends ExpressionContext {
		public TerminalNode ATTRNAME() { return getToken(ScimFilterParser.ATTRNAME, 0); }
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public CriteriaContext criteria() {
			return getRuleContext(CriteriaContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(ScimFilterParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ScimFilterParser.WS, i);
		}
		public ATTR_OPER_CRITERIAContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitATTR_OPER_CRITERIA(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LPAREN_EXPR_RPARENContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(ScimFilterParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ScimFilterParser.RPAREN, 0); }
		public List<TerminalNode> WS() { return getTokens(ScimFilterParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ScimFilterParser.WS, i);
		}
		public LPAREN_EXPR_RPARENContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitLPAREN_EXPR_RPAREN(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new NOT_EXPRContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(19);
				match(NOT);
				setState(21); 
				_errHandler.sync(this);
				_alt = 1+1;
				do {
					switch (_alt) {
					case 1+1:
						{
						{
						setState(20);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(23); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(25);
				expression(8);
				}
				break;
			case 2:
				{
				_localctx = new ATTR_PRContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(26);
				match(ATTRNAME);
				setState(28); 
				_errHandler.sync(this);
				_alt = 1+1;
				do {
					switch (_alt) {
					case 1+1:
						{
						{
						setState(27);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(30); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(32);
				match(PR);
				}
				break;
			case 3:
				{
				_localctx = new ATTR_OPER_VALUEContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(33);
				match(ATTRNAME);
				setState(35); 
				_errHandler.sync(this);
				_alt = 1+1;
				do {
					switch (_alt) {
					case 1+1:
						{
						{
						setState(34);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(37); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(39);
				operator();
				setState(41); 
				_errHandler.sync(this);
				_alt = 1+1;
				do {
					switch (_alt) {
					case 1+1:
						{
						{
						setState(40);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(43); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(45);
				value();
				}
				break;
			case 4:
				{
				_localctx = new ATTR_OPER_CRITERIAContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(47);
				match(ATTRNAME);
				setState(49); 
				_errHandler.sync(this);
				_alt = 1+1;
				do {
					switch (_alt) {
					case 1+1:
						{
						{
						setState(48);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(51); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(53);
				operator();
				setState(55); 
				_errHandler.sync(this);
				_alt = 1+1;
				do {
					switch (_alt) {
					case 1+1:
						{
						{
						setState(54);
						match(WS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(57); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(59);
				criteria();
				}
				break;
			case 5:
				{
				_localctx = new LPAREN_EXPR_RPARENContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(61);
				match(LPAREN);
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(62);
						match(WS);
						}
						} 
					}
					setState(67);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				}
				setState(68);
				expression(0);
				setState(72);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(69);
						match(WS);
						}
						} 
					}
					setState(74);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				}
				setState(75);
				match(RPAREN);
				}
				break;
			case 6:
				{
				_localctx = new LBRAC_EXPR_RBRACContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
				match(ATTRNAME);
				setState(78);
				match(LBRAC);
				setState(82);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(79);
						match(WS);
						}
						} 
					}
					setState(84);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(85);
				expression(0);
				setState(89);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(86);
						match(WS);
						}
						} 
					}
					setState(91);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				setState(92);
				match(RBRAC);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(124);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(122);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new EXPR_AND_EXPRContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(96);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(98); 
						_errHandler.sync(this);
						_alt = 1+1;
						do {
							switch (_alt) {
							case 1+1:
								{
								{
								setState(97);
								match(WS);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(100); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
						} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						setState(102);
						match(AND);
						setState(104); 
						_errHandler.sync(this);
						_alt = 1+1;
						do {
							switch (_alt) {
							case 1+1:
								{
								{
								setState(103);
								match(WS);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(106); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
						} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						setState(108);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new EXPR_OR_EXPRContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(109);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(111); 
						_errHandler.sync(this);
						_alt = 1+1;
						do {
							switch (_alt) {
							case 1+1:
								{
								{
								setState(110);
								match(WS);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(113); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
						} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						setState(115);
						match(OR);
						setState(117); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(116);
							match(WS);
							}
							}
							setState(119); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==WS );
						setState(121);
						expression(7);
						}
						break;
					}
					} 
				}
				setState(126);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CriteriaContext extends ParserRuleContext {
		public CriteriaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_criteria; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitCriteria(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CriteriaContext criteria() throws RecognitionException {
		CriteriaContext _localctx = new CriteriaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_criteria);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__0);
			setState(129); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(128);
					matchWildcard();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(131); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(133);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode ATTRNAME() { return getToken(ScimFilterParser.ATTRNAME, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << ATTRNAME))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(ScimFilterParser.EQ, 0); }
		public TerminalNode NE() { return getToken(ScimFilterParser.NE, 0); }
		public TerminalNode CO() { return getToken(ScimFilterParser.CO, 0); }
		public TerminalNode SW() { return getToken(ScimFilterParser.SW, 0); }
		public TerminalNode EW() { return getToken(ScimFilterParser.EW, 0); }
		public TerminalNode GT() { return getToken(ScimFilterParser.GT, 0); }
		public TerminalNode LT() { return getToken(ScimFilterParser.LT, 0); }
		public TerminalNode GE() { return getToken(ScimFilterParser.GE, 0); }
		public TerminalNode LE() { return getToken(ScimFilterParser.LE, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScimFilterVisitor ) return ((ScimFilterVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NE) | (1L << CO) | (1L << SW) | (1L << EW) | (1L << GT) | (1L << LT) | (1L << GE) | (1L << LE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u008e\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\7\2\16\n\2\f\2\16\2\21\13\2\3\2\3"+
		"\2\3\3\3\3\3\3\6\3\30\n\3\r\3\16\3\31\3\3\3\3\3\3\6\3\37\n\3\r\3\16\3"+
		" \3\3\3\3\3\3\6\3&\n\3\r\3\16\3\'\3\3\3\3\6\3,\n\3\r\3\16\3-\3\3\3\3\3"+
		"\3\3\3\6\3\64\n\3\r\3\16\3\65\3\3\3\3\6\3:\n\3\r\3\16\3;\3\3\3\3\3\3\3"+
		"\3\7\3B\n\3\f\3\16\3E\13\3\3\3\3\3\7\3I\n\3\f\3\16\3L\13\3\3\3\3\3\3\3"+
		"\3\3\3\3\7\3S\n\3\f\3\16\3V\13\3\3\3\3\3\7\3Z\n\3\f\3\16\3]\13\3\3\3\3"+
		"\3\5\3a\n\3\3\3\3\3\6\3e\n\3\r\3\16\3f\3\3\3\3\6\3k\n\3\r\3\16\3l\3\3"+
		"\3\3\3\3\6\3r\n\3\r\3\16\3s\3\3\3\3\6\3x\n\3\r\3\16\3y\3\3\7\3}\n\3\f"+
		"\3\16\3\u0080\13\3\3\4\3\4\6\4\u0084\n\4\r\4\16\4\u0085\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\6\20\31 \'-\65;CJT[fls\u0085\3\4\7\2\4\6\b\n\2\4\4\2\4\5"+
		"\30\30\3\2\6\16\2\u009f\2\17\3\2\2\2\4`\3\2\2\2\6\u0081\3\2\2\2\b\u0089"+
		"\3\2\2\2\n\u008b\3\2\2\2\f\16\5\4\3\2\r\f\3\2\2\2\16\21\3\2\2\2\17\r\3"+
		"\2\2\2\17\20\3\2\2\2\20\22\3\2\2\2\21\17\3\2\2\2\22\23\7\2\2\3\23\3\3"+
		"\2\2\2\24\25\b\3\1\2\25\27\7\17\2\2\26\30\7\27\2\2\27\26\3\2\2\2\30\31"+
		"\3\2\2\2\31\32\3\2\2\2\31\27\3\2\2\2\32\33\3\2\2\2\33a\5\4\3\n\34\36\7"+
		"\30\2\2\35\37\7\27\2\2\36\35\3\2\2\2\37 \3\2\2\2 !\3\2\2\2 \36\3\2\2\2"+
		"!\"\3\2\2\2\"a\7\22\2\2#%\7\30\2\2$&\7\27\2\2%$\3\2\2\2&\'\3\2\2\2\'("+
		"\3\2\2\2\'%\3\2\2\2()\3\2\2\2)+\5\n\6\2*,\7\27\2\2+*\3\2\2\2,-\3\2\2\2"+
		"-.\3\2\2\2-+\3\2\2\2./\3\2\2\2/\60\5\b\5\2\60a\3\2\2\2\61\63\7\30\2\2"+
		"\62\64\7\27\2\2\63\62\3\2\2\2\64\65\3\2\2\2\65\66\3\2\2\2\65\63\3\2\2"+
		"\2\66\67\3\2\2\2\679\5\n\6\28:\7\27\2\298\3\2\2\2:;\3\2\2\2;<\3\2\2\2"+
		";9\3\2\2\2<=\3\2\2\2=>\5\6\4\2>a\3\2\2\2?C\7\23\2\2@B\7\27\2\2A@\3\2\2"+
		"\2BE\3\2\2\2CD\3\2\2\2CA\3\2\2\2DF\3\2\2\2EC\3\2\2\2FJ\5\4\3\2GI\7\27"+
		"\2\2HG\3\2\2\2IL\3\2\2\2JK\3\2\2\2JH\3\2\2\2KM\3\2\2\2LJ\3\2\2\2MN\7\24"+
		"\2\2Na\3\2\2\2OP\7\30\2\2PT\7\25\2\2QS\7\27\2\2RQ\3\2\2\2SV\3\2\2\2TU"+
		"\3\2\2\2TR\3\2\2\2UW\3\2\2\2VT\3\2\2\2W[\5\4\3\2XZ\7\27\2\2YX\3\2\2\2"+
		"Z]\3\2\2\2[\\\3\2\2\2[Y\3\2\2\2\\^\3\2\2\2][\3\2\2\2^_\7\26\2\2_a\3\2"+
		"\2\2`\24\3\2\2\2`\34\3\2\2\2`#\3\2\2\2`\61\3\2\2\2`?\3\2\2\2`O\3\2\2\2"+
		"a~\3\2\2\2bd\f\t\2\2ce\7\27\2\2dc\3\2\2\2ef\3\2\2\2fg\3\2\2\2fd\3\2\2"+
		"\2gh\3\2\2\2hj\7\20\2\2ik\7\27\2\2ji\3\2\2\2kl\3\2\2\2lm\3\2\2\2lj\3\2"+
		"\2\2mn\3\2\2\2n}\5\4\3\noq\f\b\2\2pr\7\27\2\2qp\3\2\2\2rs\3\2\2\2st\3"+
		"\2\2\2sq\3\2\2\2tu\3\2\2\2uw\7\21\2\2vx\7\27\2\2wv\3\2\2\2xy\3\2\2\2y"+
		"w\3\2\2\2yz\3\2\2\2z{\3\2\2\2{}\5\4\3\t|b\3\2\2\2|o\3\2\2\2}\u0080\3\2"+
		"\2\2~|\3\2\2\2~\177\3\2\2\2\177\5\3\2\2\2\u0080~\3\2\2\2\u0081\u0083\7"+
		"\3\2\2\u0082\u0084\13\2\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\7\3"+
		"\2\2\u0088\7\3\2\2\2\u0089\u008a\t\2\2\2\u008a\t\3\2\2\2\u008b\u008c\t"+
		"\3\2\2\u008c\13\3\2\2\2\25\17\31 \'-\65;CJT[`flsy|~\u0085";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}