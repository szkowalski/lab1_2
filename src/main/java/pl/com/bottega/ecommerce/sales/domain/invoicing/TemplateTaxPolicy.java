package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;

public class TemplateTaxPolicy implements TaxPolicy {

    @Override
    public Tax calculateTax(ProductType productType, Money net) throws IllegalArgumentException {
        BigDecimal ratio;
        String desc;

        switch (productType) {
            case DRUG:
                ratio = BigDecimal.valueOf(0.05);
                desc = "5% (D)";
                break;
            case FOOD:
                ratio = BigDecimal.valueOf(0.07);
                desc = "7% (F)";
                break;
            case STANDARD:
                ratio = BigDecimal.valueOf(0.23);
                desc = "23%";
                break;
            default:
                throw new IllegalArgumentException(productType + " not handled");
        }

        Money taxValue = net.multiplyBy(ratio);
        return new Tax(taxValue, desc);
    }
}
