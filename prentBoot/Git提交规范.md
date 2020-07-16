# Git提交规范

**一般只用2.1**

[完整参考](http://www.ruanyifeng.com/blog/2016/01/commit_message_change_log.html)
实际项目中实践过后，感觉很实用，检索起来很方便。

## 1. 提交规范的必要性

Git每次提交代码，需要填写commit message（提交说明），否则不允许提交。

提交一般是尽可能原子性，粒度尽可能小。通常一个项目中，提交的纪录都有k级别，为了便于搜索查找，我们可以采用三段式结构。

## 2. 三段式提交说明

每次提交，commit message都包括三个部分：Header，Body和Footer。
其中Header是必需的，Body和Footer可以省略。



```xml
<type>(<scope>): <subject>
// 空一行
<body>
// 空一行
<footer>
```

### 2.1 Header

Header部分只有一行，包含三个字段：`type`（必须）、 `scope`（可选）、 `subject`（必须）。

(1) type用于说明commit的类别，有如下几个字段标识。



```bash
- feat：新功能/需求（feature）
- fix：修补bug
- docs：文档（document）
- style： 格式（不影响代码运行的变动）
- refactor： 重构（不是新增功能，也不是修改bug的代码变动，如重构代码设计等，）
- test：增加测试（如单元测试等）
- chore：构建过程或辅助工具的变动（如脚本文件，配置文件等）
```

如果type为feat和fix，则该 commit 将肯定出现在 Change log 之中。其他情况（docs、chore、style、refactor、test）由你决定，要不要放入 Change log，建议是不要。

(2) scope
scope用于说明 commit 影响的范围，比如数据层、控制层、视图层等等，视项目不同而不同。

(3) subject
subject是 commit 目的的简短描述，不超过50个字符。

- 以动词开头，使用第一人称现在时，比如change，而不是changed或changes
- 第一个字母小写
- 结尾不加句号（.）

### 2.2 Body

Body 部分是对本次 commit 的详细描述，可以分成多行。下面是一个范例。



```php
More detailed explanatory text, if necessary.  Wrap it to 
about 72 characters or so. 

Further paragraphs come after blank lines.

- Bullet points are okay, too
- Use a hanging indent
```

有两个注意点。

（1）使用第一人称现在时，比如使用change而不是changed或changes。

（2）应该说明代码变动的动机，以及与以前行为的对比。

### 2.3 Footer

Footer 部分只用于两种情况。

（1）不兼容变动

如果当前代码与上一个版本不兼容，则 Footer 部分以BREAKING CHANGE开头，后面是对变动的描述、以及变动理由和迁移方法。

（2）关闭 Issue

如果当前 commit 针对某个issue，那么可以在 Footer 部分关闭这个 issue 。



```css
Closes #234
Closes #123, #245, #992
```

### 2.4 Revert

还有一种特殊情况，如果当前 commit 用于撤销以前的 commit，则必须以revert:开头，后面跟着被撤销 Commit 的 Header。



```csharp
revert: feat(pencil): add 'graphiteWidth' option

This reverts commit 667ecc1654a317a13331b17617d973392f415f02.
```

Body部分的格式是固定的，必须写成This reverts commit <hash>.，其中的hash是被撤销 commit 的 SHA 标识符。

如果当前 commit 与被撤销的 commit，在同一个发布（release）里面，那么它们都不会出现在 Change log 里面。如果两者在不同的发布，那么当前 commit，会出现在 Change log 的Reverts小标题下面。