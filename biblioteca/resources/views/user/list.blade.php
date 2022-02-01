@extends('user.layouts.app')

@section('content')
    <div class="card-header">{{ __('Dashboard') }}</div>
    <div class="col-lg-1">
    </div>
    <div class="card-body">
        @if (session('status'))
            <div class="alert alert-success" role="alert">
                {{ session('status') }}
            </div>
        @endif
        <div class="col-lg-1">
            <a class="btn btn-success" href="{{ route('user.create') }}">Add</a>
        </div>
    <table class="table table-bordered">
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Data Nascimento</th>
            <th>Email</th>
            <th>Telemovel</th>
            <th>Nome Utilizador</th>
            <th>Password</th>
            <th>image</th>
            <th width="280px">Action</th>
        </tr>
        @php
            $i = 0;
        @endphp
        @foreach ($users as $user)
            <tr>
                <td>{{ ++$i }}</td>
                <td>{{ $user->name }}</td>
                <td>{{ $user->date }}</td>
                <td>{{ $user->email }}</td>
                <td>{{ $user->phone }}</td>
                <td>{{ $user->username }}</td>
                <td>{{ $user->password }}</td>
                <td>{{ $user->image }}</td>
                <td>
                    <form action="{{ route('user.destroy',$user->id) }}" method="POST">
                        <a class="btn btn-info" href="{{ route('user.show',$user->id) }}">Show</a>
                        <a class="btn btn-primary" href="{{ route('user.edit',$user->id) }}">Edit</a>
                        @csrf
                        @method('DELETE')
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        @endforeach
    </table>

    

    {{ __('You are logged in!') }}
</div>
@endsection
