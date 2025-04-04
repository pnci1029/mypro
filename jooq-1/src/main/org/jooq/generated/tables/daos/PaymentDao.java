/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.daos;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.generated.tables.JPayment;
import org.jooq.generated.tables.pojos.Payment;
import org.jooq.generated.tables.records.PaymentRecord;
import org.jooq.impl.DAOImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PaymentDao extends DAOImpl<PaymentRecord, Payment, UInteger> {

    /**
     * Create a new PaymentDao without any configuration
     */
    public PaymentDao() {
        super(JPayment.PAYMENT, Payment.class);
    }

    /**
     * Create a new PaymentDao with an attached configuration
     */
    public PaymentDao(Configuration configuration) {
        super(JPayment.PAYMENT, Payment.class, configuration);
    }

    @Override
    public UInteger getId(Payment object) {
        return object.getPaymentId();
    }

    /**
     * Fetch records that have <code>payment_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Payment> fetchRangeOfJPaymentId(UInteger lowerInclusive, UInteger upperInclusive) {
        return fetchRange(JPayment.PAYMENT.PAYMENT_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>payment_id IN (values)</code>
     */
    public List<Payment> fetchByJPaymentId(UInteger... values) {
        return fetch(JPayment.PAYMENT.PAYMENT_ID, values);
    }

    /**
     * Fetch a unique record that has <code>payment_id = value</code>
     */
    public Payment fetchOneByJPaymentId(UInteger value) {
        return fetchOne(JPayment.PAYMENT.PAYMENT_ID, value);
    }

    /**
     * Fetch a unique record that has <code>payment_id = value</code>
     */
    public Optional<Payment> fetchOptionalByJPaymentId(UInteger value) {
        return fetchOptional(JPayment.PAYMENT.PAYMENT_ID, value);
    }

    /**
     * Fetch records that have <code>customer_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Payment> fetchRangeOfJCustomerId(UInteger lowerInclusive, UInteger upperInclusive) {
        return fetchRange(JPayment.PAYMENT.CUSTOMER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>customer_id IN (values)</code>
     */
    public List<Payment> fetchByJCustomerId(UInteger... values) {
        return fetch(JPayment.PAYMENT.CUSTOMER_ID, values);
    }

    /**
     * Fetch records that have <code>staff_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Payment> fetchRangeOfJStaffId(UInteger lowerInclusive, UInteger upperInclusive) {
        return fetchRange(JPayment.PAYMENT.STAFF_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>staff_id IN (values)</code>
     */
    public List<Payment> fetchByJStaffId(UInteger... values) {
        return fetch(JPayment.PAYMENT.STAFF_ID, values);
    }

    /**
     * Fetch records that have <code>rental_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Payment> fetchRangeOfJRentalId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(JPayment.PAYMENT.RENTAL_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>rental_id IN (values)</code>
     */
    public List<Payment> fetchByJRentalId(Integer... values) {
        return fetch(JPayment.PAYMENT.RENTAL_ID, values);
    }

    /**
     * Fetch records that have <code>amount BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Payment> fetchRangeOfJAmount(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(JPayment.PAYMENT.AMOUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>amount IN (values)</code>
     */
    public List<Payment> fetchByJAmount(BigDecimal... values) {
        return fetch(JPayment.PAYMENT.AMOUNT, values);
    }

    /**
     * Fetch records that have <code>payment_date BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Payment> fetchRangeOfJPaymentDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(JPayment.PAYMENT.PAYMENT_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>payment_date IN (values)</code>
     */
    public List<Payment> fetchByJPaymentDate(LocalDateTime... values) {
        return fetch(JPayment.PAYMENT.PAYMENT_DATE, values);
    }

    /**
     * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Payment> fetchRangeOfJLastUpdate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(JPayment.PAYMENT.LAST_UPDATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    public List<Payment> fetchByJLastUpdate(LocalDateTime... values) {
        return fetch(JPayment.PAYMENT.LAST_UPDATE, values);
    }
}
