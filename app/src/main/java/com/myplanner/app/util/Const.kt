package com.myplanner.app.util

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.drawscope.ContentDrawScope

const val CALENDAR = "CALENDAR"
const val TODO = "TODO"
const val MEMO = "MEMO"
const val NOTI = "NOTI"
const val SETTING = "SETTING"

object NoRippleIndication : Indication {
    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return object :  IndicationInstance {
            override fun ContentDrawScope.drawIndication() {
                drawContent()
            }
        }
    }
}