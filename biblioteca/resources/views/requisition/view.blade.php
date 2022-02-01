@extends('requisition.layouts.app')

@section('content')
@if (Route::has('login'))
@auth
<div class="card-header">{{ __('Requisição') }}</div>
<div class="col-lg-1"></div>
<div class="card-body">
<div class="col-lg-1">
    <a class="btn btn-primary" href="{{ url('requisition') }}"> Back</a>
</div>
    @if (session('status'))
        <div class="alert alert-success" role="alert">
            {{ session('status') }}
        </div>
    @endif
    <table class="table table-bordered">
        <tr>
            <th>Email:</th>
            <td>{{ $requisition->email }}</td>
        </tr>
        <tr>
            <th>Titulo:</th>
            <td>{{ $requisition->title }}</td>
        </tr>
        <tr>
            <th>Data de requisição:</th>
            <td>{{ $requisition->requestDate }}</td>
        </tr>
        <tr>
            <th>Data de entrega:</th>
            <td>{{ $requisition->deliverDate }}</td>
        </tr>
        <tr>
            <th>Quantidade:</th>
            <td>{{ $requisition->quantity }}</td>
        </tr>
        <tr>
            <th>Status:</th>
            <td>{{ $requisition->status }}</td>
        </tr>
    </table>
    {{ __('You are logged in!') }}
</div>

<div class="hidden fixed top-0 right-0 px-6 py-4 sm:block">
        <a href="{{ url('/home') }}" class="text-sm text-gray-700 dark:text-gray-500 underline">Home</a>
    @else
        <a href="{{ route('login') }}" class="text-sm text-gray-700 dark:text-gray-500 underline">Log in</a>

        @if (Route::has('register'))
            <a href="{{ route('register') }}" class="ml-4 text-sm text-gray-700 dark:text-gray-500 underline">Register</a>
        @endif
    @endauth
</div>
    @endif
@endsection
