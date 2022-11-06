package com.github.org.todaybread.todaybread.steppay.customer.application;

import com.github.org.todaybread.todaybread.steppay.customer.infra.request.SteppayCreateCustomerRequest;
import com.github.org.todaybread.todaybread.steppay.customer.infra.response.SteppayCustomerResponse;

public interface SteppayCustomerService {

    SteppayCustomerResponse createCustomer(SteppayCreateCustomerRequest request);
}
