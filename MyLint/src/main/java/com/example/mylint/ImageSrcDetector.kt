package com.example.mylint

import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.LintFix
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import com.android.tools.lint.detector.api.XmlScanner
import com.android.tools.lint.detector.api.XmlScannerConstants
import org.w3c.dom.Attr
import org.w3c.dom.Element

/**
 * [https://github.com/Charlezz/LintCheck](https://github.com/Charlezz/LintCheck)
 */
@Suppress("UnstableApiUsage")
class ImageSrcDetector: Detector(), XmlScanner {
    companion object {
        val ISSUE = Issue.create(
            id = "SrcAttrImageViewLayout",
            briefDescription = "Prohibits android:src attribute",
            explanation = "android:src attributes should be replaced app:srcCompat",
            category = Category.CORRECTNESS,
            severity = Severity.ERROR,
            implementation = Implementation(
                ImageSrcDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    override fun appliesTo(folderType: ResourceFolderType): Boolean =
        folderType == ResourceFolderType.LAYOUT


    override fun getApplicableAttributes(): Collection<String>? =
        XmlScannerConstants.ALL

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        val attributeName = attribute.name
        if (!attributeName.equals("android:src")) {
            return
        }

        val srcCompatFix = LintFix.create()
            .name("Use app:srcCompat")
            .replace()
            .text("android:src")
            .with("app:srcCompat")
            .robot(false)
            .independent(true)
            .build()

        context.report(
            issue = ISSUE,
            scope = attribute,
            location = context.getValueLocation(attribute),
            message = "android:src attributes should be replaced app:srcCompat",
            quickfixData = srcCompatFix
        )
    }
}