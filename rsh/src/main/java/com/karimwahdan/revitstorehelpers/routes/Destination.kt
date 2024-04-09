package com.karimwahdan.revitstorehelpers.routes

interface Destination {val route:String}

object SelectProject: Destination {override val route="select-project"}

object Home: Destination {override val route="home"}
object Categories: Destination {override val route="categories"}
object WishList: Destination {override val route="wish-list"}
object Profile: Destination {override val route="profile"}
object Cart: Destination {override val route="carts"}
object Products: Destination {override val route="products"}
object AboutUs: Destination {override val route="about-us"}
object FAQRoute: Destination {override val route="f-a-q"}
object PaymentRoute: Destination {override val route="payment"}
object Search: Destination {override val route="search"}
object ProductShow: Destination {override val route="show-product"}
object SubCategoriesList: Destination {override val route="sub-categories"}
object ProductsList: Destination {override val route="products-list"}
object NotificationsRoute: Destination {override val route="notifications"}
object DeliveryAddressesRoute: Destination {override val route="delivery-addresses"}
object CreateAddressRoute: Destination {override val route="create-address"}
object TermsAndConditionsRoute: Destination {override val route="terms-and-conditions"}
object PrivacyPolicyRoute: Destination {override val route="privacy-policy"}
object LoginRoute: Destination {override val route="login"}
object RegisterRoute: Destination {override val route="register"}
object OrdersRoute: Destination {override val route="orders"}
object OrderDetailsRoute: Destination {override val route="order-details"}
object ChangePasswordRoute: Destination {override val route="change-password"}
object SuccessfulPaymentRoute: Destination {override val route="successful-payment"}
object ForgetPassword: Destination {override val route="forget-password"}

object ContactUsRoute: Destination {override val route="contact-us"}
object RateUsRoute: Destination {override val route="rate-us"}
object ShareProductRoute: Destination {override val route="share-product"}

object CustomerAddress: Destination {override val route="customer-address"}
object EditAccountRoute: Destination {override val route="edit-account"}