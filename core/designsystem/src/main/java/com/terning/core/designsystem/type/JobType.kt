package com.terning.core.designsystem.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.terning.core.designsystem.R

enum class JobType(
    val stringValue: String,
    @DrawableRes val drawableResId: Int,
    @StringRes val stringResId: Int,
) {
    PLAN("plan", R.drawable.ic_job_plan_24, R.string.job_type_plan),
    MARKETING("marketing", R.drawable.ic_job_marketing_24, R.string.job_type_marketing),
    SALES("sales", R.drawable.ic_job_sales_24, R.string.job_type_sales),
    ADMIN("admin", R.drawable.ic_job_accounting_24, R.string.job_type_admin),
    DESIGN("design", R.drawable.ic_job_design_24, R.string.job_type_design),
    IT("it", R.drawable.ic_job_it_24, R.string.job_type_it),
    RESEARCH("research", R.drawable.ic_bookmark_filled, R.string.job_type_research),
    ETC("etc", R.drawable.ic_job_extra_24, R.string.job_type_etc),
    TOTAL("total", R.drawable.ic_job_all_24, R.string.job_type_total), ;


    companion object {
        fun fromString(value: String?): JobType = when (value) {
            "plan" -> PLAN
            "marketing" -> MARKETING
            "admin" -> ADMIN
            "sales" -> SALES
            "design" -> DESIGN
            "it" -> IT
            "research" -> RESEARCH
            "etc" -> ETC
            else -> TOTAL
        }
    }
}