/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package git4idea.history

import com.intellij.openapi.vcs.Executor.touch
import com.intellij.vcsUtil.VcsUtil
import git4idea.test.GitSingleRepoTest
import git4idea.test.add
import git4idea.test.commit

class GitHistoryTest : GitSingleRepoTest() {

  fun `test commit message with escape sequence`() {
    touch("a.txt")
    add()
    val MESSAGE = "Before \u001B[30;47mescaped\u001B[0m after"
    commit(MESSAGE)

    val history = GitFileHistory.collectHistory(project, VcsUtil.getFilePath(myProjectRoot, "a.txt"), "-1")
    assertEquals("Commit message is incorrect", MESSAGE, history[0].commitMessage)
  }
}