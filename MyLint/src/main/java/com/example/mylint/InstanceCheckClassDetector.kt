package com.example.mylint

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import org.jetbrains.uast.UClass
import java.util.EnumSet

@Suppress("UnstableApiUsage")
class InstanceCheckClassDetector : Detector(), SourceCodeScanner {
    companion object {
        private const val ISSUE_ID = "InstanceCheckClass"
        private const val ISSUE_DESCRIPTION = "InstanceCheckClass property detected"
        private const val ISSUE_EXPLANATION = "Classes should not have a property named InstanceCheckClass"
        private val CATEGORY = Category.CORRECTNESS
        private const val PRIORITY = 6
        private val SEVERITY = Severity.WARNING

        val ISSUE = Issue.create(
            id = ISSUE_ID,
            briefDescription = ISSUE_DESCRIPTION,
            explanation = ISSUE_EXPLANATION,
            category = CATEGORY,
            priority = PRIORITY,
            severity = SEVERITY,
            implementation = Implementation(
                InstanceCheckClassDetector::class.java,
                EnumSet.of(Scope.JAVA_FILE)
            )
        )
    }


    override fun applicableSuperClasses(): List<String> = listOf(
        "android.app.Activity",
    )

    override fun visitClass(context: JavaContext, declaration: UClass) {
        val propertyList = declaration.fields
        if (propertyList.any { it.typeReference?.getQualifiedName()?.contains("InstanceCheckClass") == true }) {
            val target = declaration.fields.find { it.typeReference?.getQualifiedName()!!.contains("InstanceCheckClass") }
            context.report(ISSUE, target, context.getNameLocation(target!!), ISSUE_DESCRIPTION)
        }

    }

//    override fun getApplicableUastTypes() = listOf(UClass::class.java)
//
//    override fun createUastHandler(context: JavaContext): UElementHandler? {
//        return object : UElementHandler() {
//            override fun visitClass(node: UClass) {
//                if (!node.superTypes.any { it.canonicalText.contains("android.app.Activity") })
//                    return
//
//                val propertyList = node.fields
//                if (propertyList.any { it.typeReference?.getQualifiedName()?.contains("InstanceCheckClass") == true }) {
//                    val target = node.fields.find { it.typeReference?.getQualifiedName()!!.contains("InstanceCheckClass") }
//                    context.report(ISSUE, target, context.getNameLocation(target!!), ISSUE_DESCRIPTION)
//                }
//            }
//        }
//    }
}