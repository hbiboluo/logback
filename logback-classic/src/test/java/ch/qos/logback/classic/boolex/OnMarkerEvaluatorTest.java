package ch.qos.logback.classic.boolex;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.MarkerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.boolex.EvaluationException;


public class OnMarkerEvaluatorTest {

  
  LoggerContext lc = new LoggerContext();
  LoggingEvent event = makeEvent();
  OnMarkerEvaluator evaluator = new OnMarkerEvaluator();

  @Before
  public void before() {
    evaluator.setContext(lc);
  }
  
  @Test
  public void smoke() throws EvaluationException {
    evaluator.addMarker("M");
    evaluator.start();
   
    event.setMarker(MarkerFactory.getMarker("M"));
    assertTrue(evaluator.evaluate(event));
  }
  
  @Test
  public void nullMarkerInEvent() throws EvaluationException {
    evaluator.addMarker("M");
    evaluator.start();
    assertFalse(evaluator.evaluate(event));
  }
  
  @Test
  public void nullMarkerInEvaluator() throws EvaluationException {
    evaluator.addMarker("M");
    evaluator.start();
    assertFalse(evaluator.evaluate(event));
  }
  
  
  LoggingEvent makeEvent() {
    return new LoggingEvent("x", lc.getLogger("x"), Level.DEBUG, "msg", null, null);
  }
}
