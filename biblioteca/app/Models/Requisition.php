<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Requisition extends Model
{
    use HasFactory;
    protected $requisitions = 'requisitions';
    protected $fillable = ['email','title','requestDate','deliverDate','quantity','status'];


}
