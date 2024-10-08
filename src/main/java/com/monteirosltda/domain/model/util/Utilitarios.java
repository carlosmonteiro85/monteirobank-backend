package com.monteirosltda.domain.model.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utilitarios {

    public static String formataValor(final BigDecimal valor) {
        final DecimalFormat decimalFormatReal = new DecimalFormat("¤ ###,###,##0.00",
                new DecimalFormatSymbols(new Locale("pt", "BR")));
        return Objects.nonNull(valor) ? decimalFormatReal.format(valor) : decimalFormatReal.format(BigDecimal.ZERO);
    }
    
}
