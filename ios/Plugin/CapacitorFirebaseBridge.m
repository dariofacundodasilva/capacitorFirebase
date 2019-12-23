//
//  CapacitorFirebaseBridge.h
//  App
//
//  Created by facundo da silva on 17/12/2019.
//

#import <Foundation/Foundation.h>

@interface CapacitorFirebaseBridge : NSObject

+ (void)initializeSecondaryAppFirebase;
+ (void)log:(NSString *)message;

@end