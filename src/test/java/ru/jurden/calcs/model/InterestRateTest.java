package ru.jurden.calcs.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterestRateTest {

    private static final double DELTA = 1e-10;

    @ParameterizedTest
    @MethodSource("provideDataToCalculateI")
    void testCalculateI(double percent, double value) {
        InterestRate interestRate = new InterestRate(percent);
        double actual = interestRate.getI();
        assertEquals(value, actual, DELTA);
    }

    @ParameterizedTest
    @MethodSource("provideDataToCalculateIm")
    void testCalculateIm(double percent, int m, double value) {
        InterestRate interestRate = new InterestRate(percent);
        double actual = interestRate.getIm(m);
        assertEquals(value, actual, DELTA);
    }

    @ParameterizedTest
    @MethodSource("provideDataToCalculateB")
    void testCalculateB(double percent, double value) {
        InterestRate interestRate = new InterestRate(percent);
        double actual = interestRate.getB();
        assertEquals(value, actual, DELTA);
    }

    @ParameterizedTest
    @MethodSource("provideDataToCalculateV")
    void testCalculateV(double percent, double value) {
        InterestRate interestRate = new InterestRate(percent);
        double actual = interestRate.getV();
        assertEquals(value, actual, DELTA);
    }

    @ParameterizedTest
    @MethodSource("provideDataToCalculateD")
    void testCalculateD(double percent, double value) {
        InterestRate interestRate = new InterestRate(percent);
        double actual = interestRate.getD();
        assertEquals(value, actual, DELTA);
    }

    @ParameterizedTest
    @MethodSource("provideDataToCalculateDm")
    void testCalculateDm(double percent, int m, double value) {
        InterestRate interestRate = new InterestRate(percent);
        double actual = interestRate.getDm(m);
        assertEquals(value, actual, DELTA);
    }

    @ParameterizedTest
    @MethodSource("provideDataToCalculateI")
    void testCalculateSumVD(double percent) {
        InterestRate interestRate = new InterestRate(percent);
        double actual = interestRate.getV() + interestRate.getD();
        assertEquals(1.0, actual, DELTA);
    }

    @ParameterizedTest
    @MethodSource("provideDataToCalculateI")
    void testCalculateIVD(double percent, double value) {
        InterestRate interestRate = new InterestRate(percent);
        double actual = interestRate.getD() / interestRate.getV();
        assertEquals(value, actual, DELTA);
    }

    static Stream<Arguments> provideDataToCalculateI() {
        return Stream.of(
                Arguments.of(1.0, 0.01),
                Arguments.of(2.0, 0.02),
                Arguments.of(3.0, 0.03),
                Arguments.of(4.0, 0.04),
                Arguments.of(5.0, 0.05),
                Arguments.of(6.0, 0.06),
                Arguments.of(7.0, 0.07),
                Arguments.of(8.0, 0.08),
                Arguments.of(9.0, 0.09));
    }

    static Stream<Arguments> provideDataToCalculateIm() {
        return Stream.of(
                Arguments.of(1.0,  1, 0.01),
                Arguments.of(1.0,  2, 0.0099751242),
                Arguments.of(1.0,  4, 0.0099627172),
                Arguments.of(1.0, 12, 0.0099544573),
                Arguments.of(2.0,  1, 0.02),
                Arguments.of(2.0,  2, 0.0199009876),
                Arguments.of(2.0,  4, 0.0198517262),
                Arguments.of(2.0, 12, 0.0198189756),
                Arguments.of(3.0,  1, 0.03),
                Arguments.of(3.0,  2, 0.0297783130),
                Arguments.of(3.0,  4, 0.0296682871),
                Arguments.of(3.0, 12, 0.0295952372),
                Arguments.of(4.0,  1, 0.04),
                Arguments.of(4.0,  2, 0.0396078054),
                Arguments.of(4.0,  4, 0.0394136261),
                Arguments.of(4.0, 12, 0.0392848773),
                Arguments.of(5.0,  1, 0.05),
                Arguments.of(5.0,  2, 0.0493901531),
                Arguments.of(5.0,  4, 0.0490889377),
                Arguments.of(5.0, 12, 0.0488894854),
                Arguments.of(6.0,  1, 0.06),
                Arguments.of(6.0,  2, 0.0591260281),
                Arguments.of(6.0,  4, 0.0586953846),
                Arguments.of(6.0, 12, 0.0584106067),
                Arguments.of(7.0,  1, 0.07),
                Arguments.of(7.0,  2, 0.0688160865),
                Arguments.of(7.0,  4, 0.0682341000),
                Arguments.of(7.0, 12, 0.0678497446),
                Arguments.of(8.0,  1, 0.08),
                Arguments.of(8.0,  2, 0.0784609690),
                Arguments.of(8.0,  4, 0.0777061876),
                Arguments.of(8.0, 12, 0.0772083613),
                Arguments.of(9.0,  1, 0.09),
                Arguments.of(9.0,  2, 0.0880613017),
                Arguments.of(9.0,  4, 0.0871127234),
                Arguments.of(9.0, 12, 0.0864878797));
    }

    static Stream<Arguments> provideDataToCalculateB() {
        return Stream.of(
                Arguments.of(1.0, 0.0099503309),
                Arguments.of(2.0, 0.0198026273),
                Arguments.of(3.0, 0.0295588022),
                Arguments.of(4.0, 0.0392207131),
                Arguments.of(5.0, 0.0487901642),
                Arguments.of(6.0, 0.0582689081),
                Arguments.of(7.0, 0.0676586484),
                Arguments.of(8.0, 0.0769610411),
                Arguments.of(9.0, 0.0861776962));
    }

    static Stream<Arguments> provideDataToCalculateV() {
        return Stream.of(
                Arguments.of(1.0, 0.9900990099),
                Arguments.of(2.0, 0.9803921569),
                Arguments.of(3.0, 0.9708737864),
                Arguments.of(4.0, 0.9615384615),
                Arguments.of(5.0, 0.9523809524),
                Arguments.of(6.0, 0.9433962264),
                Arguments.of(7.0, 0.9345794393),
                Arguments.of(8.0, 0.9259259259),
                Arguments.of(9.0, 0.9174311927));
    }

    static Stream<Arguments> provideDataToCalculateD() {
        return Stream.of(
                Arguments.of(1.0, 0.0099009901),
                Arguments.of(2.0, 0.0196078431),
                Arguments.of(3.0, 0.0291262136),
                Arguments.of(4.0, 0.0384615385),
                Arguments.of(5.0, 0.0476190476),
                Arguments.of(6.0, 0.0566037736),
                Arguments.of(7.0, 0.0654205607),
                Arguments.of(8.0, 0.0740740741),
                Arguments.of(9.0, 0.0825688073));
    }

    static Stream<Arguments> provideDataToCalculateDm() {
        return Stream.of(
                Arguments.of(1.0,  1, 0.0099009900),
                Arguments.of(1.0,  2, 0.0099256195),
                Arguments.of(1.0,  4, 0.0099379649),
                Arguments.of(1.0, 12, 0.0099462066),
                Arguments.of(2.0,  1, 0.0196078431),
                Arguments.of(2.0,  2, 0.0197049140),
                Arguments.of(2.0,  4, 0.0197536900),
                Arguments.of(2.0, 12, 0.0197862969),
                Arguments.of(3.0,  1, 0.0291262135),
                Arguments.of(3.0,  2, 0.0293414436),
                Arguments.of(3.0,  4, 0.0294498554),
                Arguments.of(3.0, 12, 0.0295224269),
                Arguments.of(4.0,  1, 0.0384615384),
                Arguments.of(4.0,  2, 0.0388386486),
                Arguments.of(4.0,  4, 0.0390290570),
                Arguments.of(4.0, 12, 0.0391566885),
                Arguments.of(5.0,  1, 0.0476190476),
                Arguments.of(5.0,  2, 0.0481998541),
                Arguments.of(5.0,  4, 0.0484938103),
                Arguments.of(5.0, 12, 0.0486911117),
                Arguments.of(6.0,  1, 0.0566037735),
                Arguments.of(6.0,  2, 0.0574282752),
                Arguments.of(6.0,  4, 0.0578465532),
                Arguments.of(6.0, 12, 0.0581276674),
                Arguments.of(7.0,  1, 0.0654205607),
                Arguments.of(7.0,  2, 0.0665270219),
                Arguments.of(7.0,  4, 0.0670896495),
                Arguments.of(7.0, 12, 0.0674682692),
                Arguments.of(8.0,  1, 0.0740740740),
                Arguments.of(8.0,  2, 0.0754991027),
                Arguments.of(8.0,  4, 0.0762253914),
                Arguments.of(8.0, 12, 0.0767147761),
                Arguments.of(9.0,  1, 0.0825688073),
                Arguments.of(9.0,  2, 0.0843474295),
                Arguments.of(9.0,  4, 0.0852560028),
                Arguments.of(9.0, 12, 0.0858689941));
    }
}
