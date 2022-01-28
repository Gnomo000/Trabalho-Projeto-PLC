<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateRequisitionsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('requisitions', function (Blueprint $requisitions) {
            $requisitions->increments('id');
            $requisitions->string('email');
            $requisitions->string('title');
            $requisitions->string('requestDate');
            $requisitions->string('deliverDate');
            $requisitions->integer('quantity');
            $requisitions->string('status');
            $requisitions->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('requisitions');
    }
}
