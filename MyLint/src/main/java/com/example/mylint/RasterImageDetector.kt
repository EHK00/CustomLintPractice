package com.example.mylint

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import com.android.tools.lint.detector.api.Detector.XmlScanner
import com.android.tools.lint.detector.api.Location
import org.w3c.dom.Element

@Suppress("UnstableApiUsage")
class RasterImageDetector : Detector(), XmlScanner {
    companion object {
        val issue = Issue.create(
            id = "considerVectorImage",
            briefDescription = "Consider vector images instead of raster images",
            explanation = "",
            category = Category.CORRECTNESS,
            severity = Severity.WARNING,
            implementation = Implementation(
                RasterImageDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )

        /**
         * todo
         *
         */
    }

    override fun getApplicableElements() = listOf("bitmap")

    override fun visitElement(context: XmlContext, element: Element) {
        context.report(
            issue = issue,
            location = context.getLocation(element),
            message = "Use `<vector>` instead of `<bitmap>`"
        )
    }
}