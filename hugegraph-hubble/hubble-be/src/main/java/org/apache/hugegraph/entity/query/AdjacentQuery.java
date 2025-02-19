/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.apache.hugegraph.entity.query;

import java.util.List;
import java.util.Set;

import org.apache.hugegraph.structure.constant.Direction;
import org.apache.hugegraph.structure.graph.Edge;
import org.apache.hugegraph.structure.graph.Vertex;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdjacentQuery {

    @JsonProperty("vertex_id")
    private String vertexId;

    @JsonProperty("vertex_label")
    private String vertexLabel;

    @JsonProperty("edge_label")
    private String edgeLabel;

    @JsonProperty("direction")
    private Direction direction;

    @JsonProperty("conditions")
    private List<Condition> conditions;

    @JsonProperty("adjacent_vertices")
    private Set<String> adjacentVertices;

    @JsonProperty("adjacent_edges")
    private Set<String> adjacentEdges;

    public boolean retainVertex(Vertex vertex) {
        if (this.adjacentVertices == null) {
            return true;
        }
        return !this.adjacentVertices.contains(vertex.id().toString());
    }

    public boolean retainEdge(Edge edge) {
        if (this.adjacentEdges == null) {
            return true;
        }
        return !this.adjacentEdges.contains(edge.id());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Condition {

        @JsonProperty("key")
        private String key;

        @JsonProperty("operator")
        private String operator;

        @JsonProperty("value")
        private Object value;
    }
}
