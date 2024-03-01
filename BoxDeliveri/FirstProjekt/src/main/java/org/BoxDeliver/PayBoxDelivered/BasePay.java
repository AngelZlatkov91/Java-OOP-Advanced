package org.BoxDeliver.PayBoxDelivered;



import org.BoxDeliver.common.ExceptionMessages;

public class BasePay implements Pay {
    private static final double PRICE_FOR_TWO_KILOGRAMS = 7.30;
    private static final double PRICE_FOR_EVERY_KILOGRAMS = 2.00;
    private static final double PRICE_CASH_ON_DELIVERY_SERVICE = 2.00;
    private String typeClient;
    private String typePay;
    boolean isService;


    public BasePay(String typeClient, String typePay) {
        this.setTypeClient(typeClient);
        this.setTypePay(typePay);
        this.isService= false;
    }


    public String getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(String typeClient) {
        if (!typeClient.equals("Sender") &&!typeClient.equals("Deliver") ) {
            throw new IllegalArgumentException(ExceptionMessages.PAY_MUS_DO_IT_SENDER_OR_DELIVER);
        }
        this.typeClient = typeClient;
    }

    public String getTypePay() {
        return typePay;
    }

    @Override
    public double price(String trackingNumber,double getKilograms) {
        if (trackingNumber == null || getKilograms < 0) {
            throw new IllegalArgumentException(ExceptionMessages.PAY_MUS_HAVE_TRACK_NUMBER_AND_POSITIVE_KILOGRAMS);
        }
        double kilograms = getKilograms;
        double price = PRICE_FOR_TWO_KILOGRAMS;
        if (kilograms >= 2.50) {
            double restKilograms = kilograms - 2.50;
            price += Math.ceil(restKilograms) * PRICE_FOR_EVERY_KILOGRAMS;
        }
          if (this.isService) {
              price += PRICE_CASH_ON_DELIVERY_SERVICE;
          }
          return price;
    }

    public void setTypePay(String typePay) {
        if (!typePay.equals("Cash") && !typePay.equals("Card")) {
            throw new IllegalArgumentException(ExceptionMessages.PAY_MUS_PAY_ONLY_WITH_CARD_OR_CASH);
        }

        this.typePay = typePay;
    }

    public boolean isService() {
        return isService;
    }

    public void setService(boolean service) {
       this.isService = service;
    }
}
