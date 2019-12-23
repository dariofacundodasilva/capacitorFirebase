import { WebPlugin } from '@capacitor/core';
import { CapacitorFirebasePluginPlugin } from './definitions';

export class CapacitorFirebasePluginWeb extends WebPlugin implements CapacitorFirebasePluginPlugin {
  constructor() {
    super({
      name: 'CapacitorFirebasePlugin',
      platforms: ['web']
    });
  }

  async echo(options: { value: string }): Promise<{value: string}> {
    console.log('ECHO', options);
    return options;
  }
}

const CapacitorFirebasePlugin = new CapacitorFirebasePluginWeb();

export { CapacitorFirebasePlugin };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(CapacitorFirebasePlugin);
