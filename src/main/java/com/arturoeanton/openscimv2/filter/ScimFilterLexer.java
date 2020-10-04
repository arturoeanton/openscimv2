// Generated from /Users/arturoeliasanton/github/arturoeanton/openscimv2/src/main/resources/ScimFilter.g4 by ANTLR 4.8
package com.arturoeanton.openscimv2.filter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ScimFilterLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, EQ=4, NE=5, CO=6, SW=7, EW=8, GT=9, LT=10, GE=11, 
		LE=12, NOT=13, AND=14, OR=15, PR=16, LPAREN=17, RPAREN=18, LBRAC=19, RBRAC=20, 
		WS=21, ATTRNAME=22, ANY=23, EOL=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "EQ", "NE", "CO", "SW", "EW", "GT", "LT", "GE", 
			"LE", "NOT", "AND", "OR", "PR", "LPAREN", "RPAREN", "LBRAC", "RBRAC", 
			"WS", "ATTRNAME", "ANY", "EOL"
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


	public ScimFilterLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ScimFilter.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u0081\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25"+
		"\3\26\3\26\3\27\6\27u\n\27\r\27\16\27v\3\30\3\30\3\31\6\31|\n\31\r\31"+
		"\16\31}\3\31\3\31\2\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\3"+
		"\2\23\4\2GGgg\4\2SSss\4\2PPpp\4\2EEee\4\2QQqq\4\2UUuu\4\2YYyy\4\2IIii"+
		"\4\2VVvv\4\2NNnn\4\2CCcc\4\2FFff\4\2TTtt\4\2RRrr\7\2/\60\62<C\\aac|\6"+
		"\2$$*+]]__\4\2\13\f\16\17\2\u0082\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5\65\3\2"+
		"\2\2\7:\3\2\2\2\t@\3\2\2\2\13C\3\2\2\2\rF\3\2\2\2\17I\3\2\2\2\21L\3\2"+
		"\2\2\23O\3\2\2\2\25R\3\2\2\2\27U\3\2\2\2\31X\3\2\2\2\33[\3\2\2\2\35_\3"+
		"\2\2\2\37c\3\2\2\2!f\3\2\2\2#i\3\2\2\2%k\3\2\2\2\'m\3\2\2\2)o\3\2\2\2"+
		"+q\3\2\2\2-t\3\2\2\2/x\3\2\2\2\61{\3\2\2\2\63\64\7$\2\2\64\4\3\2\2\2\65"+
		"\66\7v\2\2\66\67\7t\2\2\678\7w\2\289\7g\2\29\6\3\2\2\2:;\7h\2\2;<\7c\2"+
		"\2<=\7n\2\2=>\7u\2\2>?\7g\2\2?\b\3\2\2\2@A\t\2\2\2AB\t\3\2\2B\n\3\2\2"+
		"\2CD\t\4\2\2DE\t\2\2\2E\f\3\2\2\2FG\t\5\2\2GH\t\6\2\2H\16\3\2\2\2IJ\t"+
		"\7\2\2JK\t\b\2\2K\20\3\2\2\2LM\t\2\2\2MN\t\b\2\2N\22\3\2\2\2OP\t\t\2\2"+
		"PQ\t\n\2\2Q\24\3\2\2\2RS\t\13\2\2ST\t\n\2\2T\26\3\2\2\2UV\t\t\2\2VW\t"+
		"\2\2\2W\30\3\2\2\2XY\t\13\2\2YZ\t\2\2\2Z\32\3\2\2\2[\\\t\4\2\2\\]\t\6"+
		"\2\2]^\t\n\2\2^\34\3\2\2\2_`\t\f\2\2`a\t\4\2\2ab\t\r\2\2b\36\3\2\2\2c"+
		"d\t\6\2\2de\t\16\2\2e \3\2\2\2fg\t\17\2\2gh\t\16\2\2h\"\3\2\2\2ij\7*\2"+
		"\2j$\3\2\2\2kl\7+\2\2l&\3\2\2\2mn\7]\2\2n(\3\2\2\2op\7_\2\2p*\3\2\2\2"+
		"qr\7\"\2\2r,\3\2\2\2su\t\20\2\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2"+
		"\2w.\3\2\2\2xy\n\21\2\2y\60\3\2\2\2z|\t\22\2\2{z\3\2\2\2|}\3\2\2\2}{\3"+
		"\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\b\31\2\2\u0080\62\3\2\2\2\5\2"+
		"v}\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}