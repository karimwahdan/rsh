package com.karimwahdan.revitstorehelpers.routes

const val prefixCustomer = "customer"
const val prefixOwner = "owner"
const val BASE_URL = "https://store.egrevit.com/api/"
const val prefixCategories = "categories"
const val prefixFaq = "faq"
const val prefixAboutUs = "about-us"
const val prefixTermsAndConditions = "terms-and-conditions"
const val prefixPrivacy = "privacy-policy"
const val prefixNotifications="notifications"

const val prefixProducts="products"
const val prefixAddresses = "addresses"
const val prefixOrders = "orders"
const val indexUrl = "index"
const val indexAllUrl = "index-all"
const val viewUrl = "view"
const val storeUrl = "store"
const val deleteUrl = "delete"
const val project_id = "project_id"
const val customer_id = "customer_id"
const val id="id"

class OrderStatusValue{
    companion object{
        val inProgress=1
        val sent=2
        val approved=3
        val inDelivery=4
        val delivered=5
        val rejected=6
        val onHold=7
        val cancelled=8
        val returned=9
        val placed=10
        val preparing=11
        val done=12
        val ready=13

    }
}
