/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package selector

import (
	"errors"
	"strings"
)

func init() {
	s := &ipSelector{}
	s.indexes = make(map[string]int)
	Register("ip", s)
	Register("dns", s)
}

type ipSelector struct {
	indexes map[string]int
}

// Select implements Selector interface.
// Select will return the address in the serviceName sequentially.
// The first address will be returned after reaching the end of the addresses.
func (s *ipSelector) Select(serviceName string) (*Node, error) {
	if len(serviceName) == 0 {
		return nil, errors.New("serviceName empty")
	}

	num := strings.Count(serviceName, ",") + 1
	if num == 1 {
		return &Node{
			ServiceName: serviceName,
			Address:     serviceName,
			HasNext:     false,
		}, nil
	}

	nextIndex := 0
	if index, ok := s.indexes[serviceName]; ok {
		nextIndex = index
	}

	addresses := strings.Split(serviceName, ",")
	address := addresses[nextIndex]
	nextIndex = (nextIndex + 1) % num
	s.indexes[serviceName] = nextIndex

	node := &Node{
		ServiceName: serviceName,
		Address:     address,
	}
	if nextIndex > 0 {
		node.HasNext = true
	}
	return node, nil
}
