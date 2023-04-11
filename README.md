# Custom Lint Practice (WIP)

여러 custom lint 테스트를 위한 프로젝트입니다.

## InstanceCheckClassDetector

Activity에서 특정 클래스를 프로퍼티로 갖고 있는 경우 경고합니다.

서드파티 라이브러리처럼 직접 @Deprecated annotation을 지정할 수 없는 경우 사용합니다.

## ImageSrcDetector

layout xml에서 android:src attribute를 사용하는 경우, app:srcCompat을 사용하도록 경고합니다.

## Custom Lint?

Lint는 안드로이드 앱 개발에서 사용되는 정적 분석 도구입니다. 소스 코드를 검사하여 코드 품질을 향상시킵니다.

Custom Lint는 안드로이드 개발자가 자체적으로 만든 린트 규칙을 추가하여 Lint가 검사하는 내용을 확장할 수 있도록 해주는 기능입니다.

프로젝트에서 사용하는 코딩 규칙이나 스타일에 따라 팀 내에서 일관된 코딩 스타일을 유지할 수 있도록 도와줍니다.

## 린트 구현 방법

체크하려는 소스코드에 따라, XmlScanner, SourceCodeScanner 인터페이스를 구현합니다.

## 참고

[http://googlesamples.github.io/android-custom-lint-rules/api-guide/basics.md.html](http://googlesamples.github.io/android-custom-lint-rules/api-guide/basics.md.html)

[https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-main/docs/lint_guide.md](https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-main/docs/lint_guide.md)

[https://github.com/Charlezz/LintCheck](https://github.com/Charlezz/LintCheck)