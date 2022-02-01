@extends('user.layouts.app')

@section('content')
@if (Route::has('login'))
@auth
<div class="card-header">{{ __('Ver User') }}</div>
<div class="col-lg-1"></div>
<div class="card-body">
<div class="col-lg-1">
    <a class="btn btn-primary" href="{{ url('user') }}"> Back</a>
</div>
    @if (session('status'))
        <div class="alert alert-success" role="alert">
            {{ session('status') }}
        </div>
    @endif
    <table class="table table-bordered">
        <tr>
            <th>Email:</th>
            <td>{{ $user->name }}</td>
        </tr>
        <tr>
            <th>Titulo:</th>
            <td>{{ $user->date }}</td>
        </tr>
        <tr>
            <th>Data de requisição:</th>
            <td>{{ $user->email }}</td>
        </tr>
        <tr>
            <th>Data de entrega:</th>
            <td>{{ $user->phone }}</td>
        </tr>
        <tr>
            <th>Quantidade:</th>
            <td>{{ $user->username }}</td>
        </tr>
        <tr>
            <th>Status:</th>
            <td>{{ $user->password }}</td>
        </tr>
        <tr>
            <th>Status:</th>
            <td>{{ $user->image }}</td>
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
