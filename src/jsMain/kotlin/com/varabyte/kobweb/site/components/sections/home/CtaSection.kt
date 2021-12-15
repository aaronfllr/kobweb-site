package com.varabyte.kobweb.site.components.sections.home

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.asAttributeBuilder
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.navigation.Link
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRight
import com.varabyte.kobweb.silk.components.icons.fa.FaDiscord
import com.varabyte.kobweb.silk.components.icons.fa.FaStar
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.focus
import com.varabyte.kobweb.silk.components.style.hover
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.Text
import com.varabyte.kobweb.silk.theme.SilkTheme
import com.varabyte.kobweb.silk.theme.colors.rememberColorMode
import com.varabyte.kobweb.site.components.style.boxShadow
import org.jetbrains.compose.web.css.*

val CtaGridItemStyle = ComponentStyle("cta-grid-item") {
    base {
        Modifier
            .color(SilkTheme.palettes[colorMode].color)
            .textDecorationLine(TextDecorationLine.None)
            .transitionProperty("color")
            .transitionDuration(50.ms)
    }

    val linkColorModifier = Modifier.color(SilkTheme.palettes[colorMode].link.default)
    hover { linkColorModifier }
    focus { linkColorModifier }
}

@Composable
private fun CtaGridItem(
    text: String,
    subText: String,
    href: String,
    content: @Composable () -> Unit = {}
) {
    val colorMode by rememberColorMode()
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(33.33.percent).height(300.px).padding(4.em).boxShadow(colorMode)
    ) {
        Link(href, attrs = CtaGridItemStyle.toModifier().asAttributeBuilder()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                content()
                Text(
                    text,
                    Modifier.fontSize(1.25.em).textAlign(TextAlign.Center)
                )
                Text(
                    subText,
                    Modifier
                        .lineHeight(1.5)
                        .margin(top = 1.cssRem, bottom = 1.cssRem)
                        .opacity(70.percent)
                        .textAlign(TextAlign.Center)
                )
            }
        }
    }
}

/**
 * A "call-to-action" section which includes buttons that direct the user to take actions that will help them learn
 * and support Kobweb.
 */
@Composable
fun CtaSection() {
    Box(
        Modifier.margin(top = 6.em),
        contentAlignment = Alignment.Center,
    ) {
        Row (Modifier.flexWrap(FlexWrap.Nowrap)) {
            CtaGridItem("Get started", "Create a Web Compose website from scratch with Markdown support and live reloading, in under 10 seconds.", "/docs") {
                FaArrowRight(Modifier.fontSize(32.px).margin(12.px))
            }
            CtaGridItem("Star & Contribute", "Kobweb is fully open source and community driven. We invite you to help make Kobweb the best web development framework!", "https://github.com/varabyte/kobweb") {
                FaStar(Modifier.fontSize(32.px).margin(12.px))
            }
            CtaGridItem("Join the Community", "Join our community for instant support and great conversations about the future of the Kobweb.", "https://discord.gg/5NZ2GKV5Cs") {
                FaDiscord(Modifier.fontSize(32.px).margin(12.px))
            }
        }
    }
}