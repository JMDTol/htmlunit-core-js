package net.sourceforge.htmlunit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.sourceforge.htmlunit.corejs.javascript.Context;
import net.sourceforge.htmlunit.corejs.javascript.ContextAction;
import net.sourceforge.htmlunit.corejs.javascript.Script;

/**
 * Test for {@link Context#decompileScript(Script, int)}.
 * @author Marc Guillemot
 */
public class DecompileTest {

	/**
	 * As of head of trunk on 30.09.09, decompile of "new Date()" returns "new Date" without parentheses.
	 * @see <a href="https://bugzilla.mozilla.org/show_bug.cgi?id=519692">Bug 519692</a> 
	 */
	@Test
	public void newObject0Arg() {
		final String source = "var x = new Date().getTime();";
		final ContextAction action = new ContextAction() {
            @Override
			public Object run(final Context cx) {
				final Script script = cx.compileString(source, "my script", 0, null);
				assertEquals(source, cx.decompileScript(script, 4).trim());
				return null;
			}
		};
		Utils.runWithAllOptimizationLevels(action);
	}
}
