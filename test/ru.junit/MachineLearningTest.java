package ru.junit;

import ru.machinelearn.*;

import static org.junit.Assert.*;

public class MachineLearningTest {

    @org.junit.Test
    public void testFreqCounting() {
        String largeFile="large.txt";//Arrange
        Controller.freqCounting(largeFile);//Act
        assertFalse(Controller.freq.isEmpty());//Assert
    }

    @org.junit.Test
    public void main() {
    }
    @org.junit.Test
    public void testProbCounting() {
        //Arrange
        String largeFile="large.txt";
        String txt="Могу творить, могу и натворить!\n";
        //Act
        testFreqCounting();
        Controller.probCounting(txt,largeFile);
        //Assert
        assertSame(largeFile, Model.largeName);
        assertTrue(Controller.probability>Controller.THRESHOLD);
    }
}
