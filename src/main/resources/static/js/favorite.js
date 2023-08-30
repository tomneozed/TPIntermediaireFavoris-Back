angular.module('favoriteApp', [])
    .controller('FavoritesController', function($scope, $http) {

        $scope.categories = [];
        $scope.realCategories = [];
        $scope.favorites = [];

        $scope.category = {
            filter: 0
        };

        $scope.mode = 'view';

        $scope.favorite = {};

        $scope.setMode = function(text) {
            if (text === 'creation') {
                $scope.realCategories = $scope.categories.filter(function(c) { return c.id !== 0 });
                var idx = $scope.realCategories.map(function(c) { return c.id }).indexOf($scope.category.filter);
                if (idx < 0) idx = 0;

                $scope.favorite = {
                    link: '',
                    label: '',
                    category: $scope.realCategories[idx].id
                }
            }
            $scope.mode = text;
        }

        $scope.cancel = function() {
            $scope.setMode('view');
        }

        $scope.validate = function() {
            $http.post('api/category/' + $scope.favorite.category + '/favorite' , { id: null, label: $scope.favorite.label, link: $scope.favorite.link }).then(
                function() {
                    $scope.refresh();
                    $scope.setMode('view');
                }, function(error) {
                    alert(error.data.message);
                }
            )
        }

        $scope.refresh = function() {
            $http.get('api/category').then(
                function(response) {
                    $scope.categories = [{id: 0, label: "Everything", references: 0}];
                    response.data.forEach(d => {
                        $scope.categories.push(d);
                        $scope.categories[0].references += d.references;
                    })

                    $http.get('api/favorite').then(
                        function(response) {
                            $scope.favorites = response.data.filter(
                                (f) =>
                                    $scope.category.filter === 0 ||
                                    f.categoryDto.id === $scope.category.filter);
                        }
                    )
                }
            )
        }

        $scope.format = function(item) {
            return (item.label + (item.id != 0 ? ' (' + item.references + ')' : ''));
        }

        $scope.refresh();
    });