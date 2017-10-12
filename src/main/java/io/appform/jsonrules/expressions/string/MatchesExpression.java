/**
 * Copyright (c) 2017 Mohammed Irfanulla S <mohammed.irfanulla.s1@gmail.com>
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
 *
 */

package io.appform.jsonrules.expressions.string;

import com.fasterxml.jackson.databind.JsonNode;
import io.appform.jsonrules.ExpressionEvaluationContext;
import io.appform.jsonrules.ExpressionType;
import io.appform.jsonrules.expressions.JsonPathBasedExpression;
import io.appform.jsonrules.expressions.preoperation.PreOperation;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Check is string at json path matches with given value
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MatchesExpression extends JsonPathBasedExpression {
	private String value;
	private boolean ignoreCase;

    public MatchesExpression() {
        super(ExpressionType.mathces);
    }

    @Builder
    public MatchesExpression(String path, boolean ignoreCase, String value, Boolean defaultResult, PreOperation<?> preoperation) {
        super(ExpressionType.mathces, path, defaultResult, preoperation);
        this.value = value;
        this.ignoreCase = ignoreCase;
    }

    @Override
    protected boolean evaluate(ExpressionEvaluationContext context, String path, JsonNode evaluatedNode) {
        if(!evaluatedNode.isTextual()) {
            return false;
        }
        final String data = evaluatedNode.asText();
        if (null != data) {
        	if (ignoreCase) {
        		return data.toLowerCase().matches(value.toLowerCase());
        	}
        	return data.matches(value);
        }
        return false;
    }
}
