package com.studyx.urgames.code2job

/* Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.json.JSONException
import org.json.JSONObject

/**
 * Represents an in-app product's listing details.
 */
class SkuDetails @Throws(JSONException::class)
constructor(private val mItemType: String, private val mJson: String) {
    val sku: String
    val type: String
    val price: String
    val priceAmountMicros: Long
    val priceCurrencyCode: String
    val title: String
    val description: String

    @Throws(JSONException::class)
    constructor(jsonSkuDetails: String) : this(IabHelper.ITEM_TYPE_INAPP, jsonSkuDetails) {
    }

    init {
        val o = JSONObject(mJson)
        sku = o.optString("productId")
        type = o.optString("type")
        price = o.optString("price")
        priceAmountMicros = o.optLong("price_amount_micros")
        priceCurrencyCode = o.optString("price_currency_code")
        title = o.optString("title")
        description = o.optString("description")
    }

    override fun toString(): String {
        return "SkuDetails:" + mJson
    }
}
