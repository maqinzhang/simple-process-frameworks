package com.opensource.frameworks.processframework.config.parser;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import com.opensource.frameworks.processframework.utils.Assert;

/**
 * @author luolishu
 * 
 */
public abstract class TagParserFactory {
	static final Map<String, TagParser> parsers = new HashMap<String, TagParser>();
	static {
		register("node", NodeTagParser.class);
		register("if", IfTagParser.class);
		register("when", WhenTagParser.class);
		register("otherwise", ElseTagParser.class);
		register("elseif", ElseIfTagParser.class);
		register("else", ElseTagParser.class);
		register("try", TryTagParser.class);
		register("catch", CatchTagParser.class);
		register("finally", FinallyTagParser.class);
		register("choose", ChooseTagParser.class);
		register("process", ProcessParser.class);
	}

	public static TagParser getParser(Element e) {
		TagParser parser = parsers.get(e.getTagName());
		Assert.notNull(parser, "Parser of tag =" + e.getTagName()
				+ " must not null!");
		return parser;
	}

	private static <T extends TagParser> void register(String name,
			Class<T> parserClaz) {
		try {
			parsers.put(name, parserClaz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
