//
//  CapacitorFirebaseBridge.m
//  App
//
//  Created by facundo da silva on 17/12/2019.
//

#import "CapacitorFirebaseBridge.h"
#import "<Crashlytics/Crashlytics.h>"
#import "Firebase.h"

@implementation CapacitorFirebaseBridge

+ (void)initializeSecondaryAppFirebase {
    NSString *filePath = [[NSBundle mainBundle] pathForResource:@"GoogleService-Info2" ofType:@"plist"];
    
    if(filePath){
        NSLog(@"GoogleService-Info2.plist found, setup: [FIRApp configureWithOptions]");
        // create firebase configure options passing .plist as content
        FIROptions *options = [[FIROptions alloc] initWithContentsOfFile:filePath];
        
        // configure FIRApp with options
        [FIRApp configureWithName:@"secondary" options:options  ];
    }
}

+ (void)log:(NSString *)message {
    CLS_LOG(@"%@", message);
}

@end
