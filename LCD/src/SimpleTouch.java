import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;

/**
 *
 */
public class SimpleTouch extends AbstractFilter {
  private float[] sample;

  /**
 * @param source
 */
public SimpleTouch(SampleProvider source) {
    super(source);
    sample = new float[sampleSize];
  }

  /**
 * @return
 */
public boolean isPressed() {
    super.fetchSample(sample, 0);
    if (sample[0] == 0)
      return false;
    return true;
  }

}

