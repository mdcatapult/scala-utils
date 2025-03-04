/*
 * Copyright 2024 Medicines Discovery Catapult
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.mdcatapult.util.concurrency
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.{ExecutionContext, Future}

/** A stub implementation of LimitedExecution that makes no attempt to actually limit execution. */
object UnlimitedLimitedExecution extends LimitedExecution with LazyLogging with LimitedExecutionFactory {

  override def create(concurrency: Int): LimitedExecution = this

  override def weighted[C, T](weight: Int)(c: C, label: String)(f: C => Future[T])(implicit ec: ExecutionContext): Future[T] =
    unlimited(c, label)(f)

}
