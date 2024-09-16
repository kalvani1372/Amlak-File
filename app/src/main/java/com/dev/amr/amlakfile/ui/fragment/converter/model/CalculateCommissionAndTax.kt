package com.dev.amr.amlakfile.ui.fragment.converter.model
class CalculateCommissionAndTax {

    // تابع محاسبه کمیسیون
    private fun calculateCommission(totalAmount: Double, commissionPercentage: Double): Double {
        return totalAmount * (commissionPercentage / 100)
    }

    // تابع محاسبه مالیات
    private fun calculateTax(amountAfterCommission: Double, taxPercentage: Double): Double {
        return amountAfterCommission * (taxPercentage / 100)
    }

    // تابع محاسبه ارزش افزوده
    private fun calculateVAT(amountAfterTax: Double, vatPercentage: Double): Double {
        return amountAfterTax * (vatPercentage / 100)
    }

    // تابع کل محاسبات
    fun calculateTotalCost(totalAmount: Double, commissionPercentage: Double, taxPercentage: Double, vatPercentage: Double): Triple<Double, Double, Double> {
        val commission = calculateCommission(totalAmount, commissionPercentage)
        val amountAfterCommission = totalAmount - commission
        val tax = calculateTax(amountAfterCommission, taxPercentage)
        val amountAfterTax = amountAfterCommission + tax
        val vat = calculateVAT(amountAfterTax, vatPercentage)
        val finalAmount = amountAfterTax + vat

        return Triple(finalAmount, tax, vat)
    }
}
