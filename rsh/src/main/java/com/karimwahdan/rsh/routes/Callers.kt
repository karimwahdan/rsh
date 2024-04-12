package com.karimwahdan.rsh.routes

import com.karimwahdan.rsh.app.config.network.RetrofitBuilder
import com.karimwahdan.rsh.routes.interfaces.AboutUsApi
import com.karimwahdan.rsh.routes.interfaces.AddressesApi
import com.karimwahdan.rsh.routes.interfaces.CategoryApi
import com.karimwahdan.rsh.routes.interfaces.CustomerApi
import com.karimwahdan.rsh.routes.interfaces.FaqApi
import com.karimwahdan.rsh.routes.interfaces.GeneralApi
import com.karimwahdan.rsh.routes.interfaces.HomeApi
import com.karimwahdan.rsh.routes.interfaces.NotificationsApi
import com.karimwahdan.rsh.routes.interfaces.OrdersApi
import com.karimwahdan.rsh.routes.interfaces.OwnerApi
import com.karimwahdan.rsh.routes.interfaces.PrivacyApi
import com.karimwahdan.rsh.routes.interfaces.ProductApi
import com.karimwahdan.rsh.routes.interfaces.SubCategoryApi
import com.karimwahdan.rsh.routes.interfaces.SystemApi
import com.karimwahdan.rsh.routes.interfaces.TermsAndConditionsApi
import com.karimwahdan.rsh.routes.interfaces.WishlistApi


class Callers() {

    fun ownerApi(): OwnerApi {return RetrofitBuilder.createService(OwnerApi::class.java)}

    fun generalApi(): GeneralApi {return RetrofitBuilder.createService(GeneralApi::class.java)}

    fun homeApi(): HomeApi {return RetrofitBuilder.createService(HomeApi::class.java)}

    fun productApi(): ProductApi {return RetrofitBuilder.createService(ProductApi::class.java)}

    fun subCategoryApi(): SubCategoryApi {return RetrofitBuilder.createService(SubCategoryApi::class.java)}

    fun categoryApi(): CategoryApi {return RetrofitBuilder.createService(CategoryApi::class.java)}

    fun customerApi(): CustomerApi {return RetrofitBuilder.createService(CustomerApi::class.java)}

    fun systemApi(): SystemApi {return RetrofitBuilder.createService(SystemApi::class.java)}

    fun ordersApi(): OrdersApi {return RetrofitBuilder.createService(OrdersApi::class.java)}

    fun addressesApi(): AddressesApi {return RetrofitBuilder.createService(AddressesApi::class.java)}

    fun faqApi(): FaqApi {return RetrofitBuilder.createService(FaqApi::class.java)}

    fun aboutUsApi(): AboutUsApi {return RetrofitBuilder.createService(AboutUsApi::class.java)}

    fun termsApi(): TermsAndConditionsApi {return RetrofitBuilder.createService(TermsAndConditionsApi::class.java)}

    fun privacyPolicyApi(): PrivacyApi {return RetrofitBuilder.createService(PrivacyApi::class.java)}

    fun notificationsApi(): NotificationsApi {return RetrofitBuilder.createService(NotificationsApi::class.java)}

    fun wishlistsApi(): WishlistApi {return RetrofitBuilder.createService(WishlistApi::class.java)}
}