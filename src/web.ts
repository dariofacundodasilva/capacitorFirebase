// import { WebPlugin } from '@capacitor/core';
// import { CapacitorFirebasePlugin} from './definitions';

// export class CapacitorFirebasePluginWeb extends WebPlugin implements CapacitorFirebasePlugin {
//   constructor() {
//     super({
//       name: 'CapacitorFirebasePlugin',
//       platforms: ['web']
//     });
//   }

//   async echo(options: { value: string }): Promise<{value: string}> {
//     console.log('ECHO', options);
//     return options;
//   }

//   async initializeSecondaryAppFirebase(options: { value: string }): Promise<{value: string}> {
//     console.log('initializeSecondaryAppFirebase', options);
//     return options;
//   }

//   async getApps(options: { value: string }): Promise<{value: string}> {
//     console.log('getApps', options);
//     return options;
//   }
// }

// const CapacitorFirebasePlugin = new CapacitorFirebasePluginWeb();

// export { CapacitorFirebasePlugin };

// import { registerWebPlugin } from '@capacitor/core';
// registerWebPlugin(CapacitorFirebasePlugin);
