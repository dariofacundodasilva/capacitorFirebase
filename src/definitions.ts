declare module "@capacitor/core" {
  interface PluginRegistry {
    CapacitorFirebasex: CapacitorFirebasexPlugin;
  }
}

export interface CapacitorFirebasexPlugin {

  initializeSecondaryAppFirebase(options: { 
    apiKey: string,
    authDomain:string,
    databaseURL:string,
    projectId:string,
    storageBucket:string,
    messagingSenderId:string,
    appId:string,
    measurementId:string
  }): Promise<{}>;

  initRemoteConfig(options: { minimumFetchInterval: number }): Promise<{}>;
  initSecondaryRemoteConfig(options: { projectId: string, minimumFetchInterval: number }): Promise<{}>;
  fetch(options: { secondary: boolean, minimumFetchInterval: number }): Promise<{}>;
  activate(options: { secondary: boolean, minimumFetchInterval: number }): Promise<{}>;
  fetchAndActivate(options: { secondary: boolean, minimumFetchInterval: number }): Promise<{}>;
  getString(options: { secondary: boolean, key: string }): Promise<{value:string}>;
  getNumber(options: { secondary: boolean, key: string }): Promise<{value:number}>;
  getBytes(options: { secondary: boolean, key: string }): Promise<{}>;
  getBytes(options: { secondary: boolean, key: string }): Promise<{}>;
  getBoolean(options: { secondary: boolean, key: string }): Promise<{value:boolean}>;

  setUser(options: { id: string, name: string, email:string }): Promise<{}>;
  setCrashlyticsUserId(options: { id: string }): Promise<{}>;
  setCrashlyticsCollectionEnabled(options:{}): Promise<{}>;
  sendCrash(options:{}): Promise<{}>;
  logException(options:{message:string, stacktrace:object}): Promise<{}>;
  log(options:{message:string}): Promise<{}>;

  echo(options: { value: string }): Promise<{value: string}>;
}
