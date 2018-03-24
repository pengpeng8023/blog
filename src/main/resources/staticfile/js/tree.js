/**
 * Created by Administrator on 2018/2/8.
 */
var id = 1000;
var colorBg = ["#93b9da","#96e9b3","#dcbcae","#c0a7ff","#ffce60","#dc9ad6","#5bd4ff","#ffabab",
    "#86c63e","#31ba90","#73d5d4","#2ae2e3","#5b95ff","#1262f4",
    "#eb70c9","#cf38a5","#a505c2","#ed4151","#fce132","#f79245"];

Vue.component('show-tree',{
    template:`<el-tree
                class='show_tree'
	            :data='treeData'
	            :props='defaultProps'
	            :render-content='renderContent'
	            :load='loadNode'
            	lazy
	            :default-expand-all='false'
	            :expand-on-click-node='false'
	            :filter-node-method='filterNode'
	            ref='tree' 
	            @current-change = 'treeClick'>
	    </el-tree>`,
    data:function(){
        return{
            filterText: '',//关键字过滤
            treeNode:{
                name:'',
                code:''
            },
            treeData:[],
            defaultProps: {
                label: 'TYPE_NAME',
                children:null,
            }
        }
    },
    mounted() {
        this.http('000');
    },
    methods:{
        http(code) {
            var _this = this;
            invocationMode("get", API.NOTETYPE, "",{typeCode:code,type:'show'}, function(data) {
				console.log(data.data);
                _this.treeData = data.data;
                return data.data;
            });
        },
        loadNode(node, resolve){
			console.log(node);
     	    console.log(resolve);
            if (node.level === 0) {
                return resolve(node.data);
            }
            if(node.data.TYPE_NO != ''){
                invocationMode("get", API.NOTETYPE, "",{typeCode:node.data.TYPE_NO}, function(data) {
					console.log(data);
                    return resolve(data.data);
                });
            }else{
                return resolve([]);
            }
        },
        treeSure(){
            this.$emit('tree-sures',this.treeNode);
            this.dialogVisible = false;
        },
        treeClick(data,node){
            this.treeNode = {
                name:data.TYPE_NAME,
                code:data.TYPE_NO
            }
            this.$emit('tree-click',this.treeNode);
        },
        filterNode(value, data) {
            if (!value) return true;
            return data.label.indexOf(value) !== -1;
        },
        append(store, data) {
            store.append({ id: id++, label: 'testtest', children: [] }, data);
        },
        remove(store, data) {
            store.remove(data);
        },
        renderContent (h, { node, data, store }) {
            var self = this;
            return h(Vue.extend({
                template: `<span>
					      <span>
					        <span class="nodeImg">
					          <em  :style="style">{{node.label.substring(0,1)}}</em>		
					        </span>
					        <span class="nodeFont">{{node.label}}</span>
					      </span>
					    </span>`,
                computed: {
                    style: function () {
                        return {'background': colorBg[Math.ceil(Math.random()*7)]}
                    }
                },
                data: function() {
                    return {
                        node: node,
                        data: data,
                        store: store,
                        pic:true,
                        append: function() {
                            self.append(store, data);
                        },
                        remove: function() {
                            self.remove(store, data);
                        }
                    }
                }
            }));
        }
    },
    watch:{
    }
});