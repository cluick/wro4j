package ro.isdc.wro.model.resource.processor.support;

import java.util.Map;

import ro.isdc.wro.model.resource.processor.ResourceProcessor;



/**
 * All implementation of this interface will contribute to the list of available processors discovered during
 * application initialization.
 * 
 * @author Alex Objelean
 * @created 1 Jun 2012
 */
public interface ProcessorProvider {
  /**
   * @return the preProcessors to contribute. The key represents the processor alias.
   */
  Map<String, ResourceProcessor> providePreProcessors();
  
  /**
   * @return the postProcessors to contribute. The key represents the processor alias.
   */
  Map<String, ResourceProcessor> providePostProcessors();
}