package com.rmso.ui

import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment

/**
 *  How to use:
 *   <string name="string_xpto"><![CDATA[String with <b>bold</b>]]></string>
 * [FONT](https://stackoverflow.com/a/69045782)
 *
 */
@Composable
fun annotatedStringResource(@StringRes id: Int, vararg formatArgs: Any) =
    buildAnnotatedString {
        val text = stringResource(id, *formatArgs)
        val spanned = remember(text) {
            HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
        append(spanned.toString())
        spanned.getSpans(0, spanned.length, Any::class.java).forEach { span ->
            val start = spanned.getSpanStart(span)
            val end = spanned.getSpanEnd(span)
            when (span) {
                is StyleSpan -> when (span.style) {
                    Typeface.BOLD ->
                        addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)

                    Typeface.ITALIC ->
                        addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)

                    Typeface.BOLD_ITALIC ->
                        addStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                            ),
                            start,
                            end,
                        )
                }

                is UnderlineSpan ->
                    addStyle(SpanStyle(textDecoration = TextDecoration.Underline), start, end)

                is ForegroundColorSpan ->
                    addStyle(SpanStyle(color = Color(span.foregroundColor)), start, end)
            }
        }
    }


/**
 * ComposeView from a xml
 *
 * How to use:
 *   <androidx.compose.ui.platform.ComposeView
 *      android:id="@+id/compose_content"/>
 *
 *  binding.composeContent.setComposableContent { //composable }
 *
 */
fun ComposeView.setComposableContent(content: @Composable () -> Unit) {
    this.apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MaterialTheme {
                content()
            }
        }
    }
}

/**
 * Create composable by Fragment
 *
 * How to use:
 *      override fun onCreateView(
 *           inflater: LayoutInflater,
 *           container: ViewGroup?,
 *           savedInstanceState: Bundle?
 * ): View = createComposeView { // Composable }
 *
 */
fun Fragment.createComposableContent(
    content: @Composable () -> Unit
) = ComposeView(requireContext()).apply {
    setComposableContent(content)
}