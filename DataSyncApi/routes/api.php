<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

// Store
// api/profile
// POST Method
// Data Formate
// {
//   "profile": {
//       "data":{
//         "first_name":"First Name",
//         "last_name":"Last Name"
//         },
        
//       "status":"create",
//       "message":"Create Profile"
//   }
// }
// Update
// api/profile/{id}
// PUT Method
// Data Formate
// {
//   "profile": {
//       "data":{
//         "first_name":"First Name Updated",
//         "last_name":"Last Name Updated"
//         },
        
//       "status":"create",
//       "message":"Create Profile"
//   }
// }
Route::resources(['profile' => 'ProfileController']);