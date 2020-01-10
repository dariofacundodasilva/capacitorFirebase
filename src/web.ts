import { WebPlugin } from '@capacitor/core';
import { CapacitorFirebasexPlugin } from './definitions';

export class CapacitorFirebasexWeb extends WebPlugin implements CapacitorFirebasexPlugin {
  constructor() {
    super({
      name: 'CapacitorFirebasex',
      platforms: ['web']
    });
  }

  async initializeSecondaryAppFirebase(options: { 
    apiKey: string,
    authDomain:string,
    databaseURL:string,
    projectId:string,
    storageBucket:string,
    messagingSenderId:string,
    appId:string,
    measurementId:string
  }): Promise<{}>{
    return options;
  }

  async initRemoteConfig(options: { minimumFetchInterval: number }): Promise<{}>{
    return options;
  }
  
  async initSecondaryRemoteConfig(options: { projectId: string, minimumFetchInterval: number }): Promise<{}>{
    return options;
  }
  async fetch(options: { secondary: boolean, minimumFetchInterval: number }): Promise<{}>{
    return options;
  }
  async activate(options: { secondary: boolean, minimumFetchInterval: number }): Promise<{}>{
    return options;
  }
  async fetchAndActivate(options: { secondary: boolean, minimumFetchInterval: number }): Promise<{}>{
    return options;
  }
  async getString(options: { secondary: boolean, key: string }): Promise<{value:string}>{
    return null;
  }
  async getNumber(options: { secondary: boolean, key: string }): Promise<{value:number}>{
    return null;
  }
  async getBytes(options: { secondary: boolean, key: string }): Promise<{}>{
    return null;
  }
  async getBoolean(options: { secondary: boolean, key: string }): Promise<{value:boolean}>{
    return null;
  }

  async setUser(options: { id: string, name: string, email:string }): Promise<{}>{
    return options;
  }
  async setCrashlyticsUserId(options: { id: string }): Promise<{}>{
    return options;
  }
  async setCrashlyticsCollectionEnabled(options:{}): Promise<{}>{
    return options;
  }
  async sendCrash(options:{}): Promise<{}>{
    return options;
  }
  async logException(options:{message:string, stacktrace:object}): Promise<{}>{
    return options;
  }
  async log(options:{message:string}): Promise<{}>{
    return options;
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
