package com.karimwahdan.rsh.routes

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
        const val inProgress=1
        const val sent=2
        const val approved=3
        const val inDelivery=4
        const val delivered=5
        const val rejected=6
        const val onHold=7
        const val cancelled=8
        const val returned=9
        const val placed=10
        const val preparing=11
        const val done=12
        const val ready=13

    }
}
