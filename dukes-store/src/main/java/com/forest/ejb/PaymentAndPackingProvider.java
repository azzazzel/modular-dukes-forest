package com.forest.ejb;

import javax.ejb.Local;

import com.forest.usecase.ecommerce.providers.PackingProvider;
import com.forest.usecase.ecommerce.providers.PaymentProvider;

@Local
public interface PaymentAndPackingProvider extends PaymentProvider, PackingProvider {

}
