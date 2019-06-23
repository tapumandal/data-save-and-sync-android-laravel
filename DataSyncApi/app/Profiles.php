<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Profiles extends Model
{
    protected $fillable = ['first_name', 'last_name', 'email', 'gender', 'mobile_number', 'state', 'country'];
}
