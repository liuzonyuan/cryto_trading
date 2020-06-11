/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Gareth Jon Lynch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gazbert.crypto.services.config.impl;

import com.gazbert.crypto.domain.engine.EngineConfig;
import com.gazbert.crypto.repository.EngineConfigRepository;
import com.gazbert.crypto.services.config.EngineConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the Engine config service.
 *
 * @author gazbert
 */
@Service("engineConfigService")
@Transactional
@ComponentScan(basePackages = {"com.gazbert.crypto.repository"})
public class EngineConfigServiceImpl implements EngineConfigService {

  private static final Logger LOG = LogManager.getLogger();
  private final EngineConfigRepository engineConfigRepository;

  @Autowired
  public EngineConfigServiceImpl(@Qualifier("engineConfigYamlRepository")
                                       EngineConfigRepository engineConfigRepository) {
    this.engineConfigRepository = engineConfigRepository;
  }

  @Override
  public EngineConfig getEngineConfig() {
    return engineConfigRepository.get();
  }

  @Override
  public EngineConfig updateEngineConfig(EngineConfig config) {
    LOG.info(() -> "About to update Engine config: " + config);
    return engineConfigRepository.save(config);
  }
}
