package br.com.sicredi.votacao.service.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sicredi.votacao.util.CalculoUtils;

@RunWith(SpringRunner.class)
public class CalculoUtilsTest {

	private static final long VALUE = 32l;
	private static final long TOTAL = 85l;
	private static final double DELTA = 0.01d;
	private static final double RESULT = 37.64d;
	
	@Test
	public void givenValueAndTotal_whenCalculatePercentual_thenReturnPercentual() {
		assertEquals(RESULT, CalculoUtils.percentual(VALUE, TOTAL), DELTA);
	}
	
	@Test
	public void givenTotalZero_whenCalculatePercentual_thenReturnPercentualZero() {
		assertEquals(CalculoUtils.ZERO, CalculoUtils.percentual(VALUE, CalculoUtils.ZERO), DELTA);
	}

}
