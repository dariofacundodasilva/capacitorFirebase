import { WebPlugin } from '@capacitor/core';
import { CapacitorFirebasexPlugin } from './definitions';

export class CapacitorFirebasexWeb extends WebPlugin implements CapacitorFirebasexPlugin {
  constructor() {
    super({
      name: 'CapacitorFirebasex',
      platforms: ['web']
    });
  }

  async echo(options: { value: string }): Promise<{value: string}> {
    console.log('ECHO', options);
    return options;
  }
}

const CapacitorFirebasex = new CapacitorFirebasexWeb();

export { CapacitorFirebasex };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(CapacitorFirebasex);
