/**
 * 
 */
package tests;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	ModelTest.class,
	ContextTest.class,
	DurationPatternTest.class,
	OccurencePatternTest.class,
	OrderPatternTest.class,
	PeriodicPatternTest.class,
	RequirementTest.class,
	RTOrderPatternTest.class,
	ScopeTest.class,
	TreeComparingVisitorTest.class,
	AssociationStanfordStrategyTest.class
})

public class JunitTestSuite {   
}  	