/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class Invoice {

    private ClientData client;

    private Money net;

    private Money gros;

    private List<InvoiceLine> items;

    private Id id;

    private Invoice(Id invoiceId, ClientData client) {
        this.id = invoiceId;
        this.client = client;
        this.items = new ArrayList<InvoiceLine>();

        this.net = Money.ZERO;
        this.gros = Money.ZERO;
    }
    public static Invoice createInvoice(ClientData client) {
        return new Invoice(Id.generate(), client);
    }

    public void addItem(InvoiceLine item) {
        items.add(item);

        net = net.add(item.getNet());
        gros = gros.add(item.getGros());
    }

    /**
     * 
     * @return immutable projection
     */
    public List<InvoiceLine> getItems() {
        return Collections.unmodifiableList(items);
    }

    public ClientData getClient() {
        return client;
    }

    public Money getNet() {
        return net;
    }

    public Money getGros() {
        return gros;
    }

}
